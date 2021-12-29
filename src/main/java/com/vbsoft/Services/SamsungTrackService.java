package com.vbsoft.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.vbsoft.Modeles.In.PKFInfo;
import com.vbsoft.Modeles.Out.TRKINF.EvnList;
import com.vbsoft.Modeles.Out.TRKINF.FlippostTrack;
import com.vbsoft.Modeles.Out.TRKINF.MatList;
import com.vbsoft.Modeles.Out.TRKINF.TRKINF;
import com.vbsoft.Modeles.Repositiries.DeliveryDAO;
import com.vbsoft.Modeles.Repositiries.TrackDAO;
import com.vbsoft.Utils.Tools;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:Constants.properties")
@Log4j2
public class SamsungTrackService extends Thread {

    @Value("${samsung.send.host}")
    private String samsungHost;

    /**
     * Track DAO.
     */
    private final TrackDAO DAO;

    /**
     * Delivery DAO.
     */
    private final DeliveryDAO DELIVERY_DAO;

    /**
     * Service tools.
     */
    private final Tools TOOLS;

    @Autowired
    public SamsungTrackService(TrackDAO dao, DeliveryDAO delivery_dao, Tools tools) {
        this.DAO = dao;
        this.DELIVERY_DAO = delivery_dao;
        this.TOOLS = tools;
    }

    public void processNewTracks() {
        List<FlippostTrack> newTracks = this.DAO.findByNewFlag(true);
        newTracks.forEach(this::sendTracksToSamsung);
    }


    private void sendTracksToSamsung(FlippostTrack track) {
        PKFInfo delivery = this.DELIVERY_DAO.findFirstByDocumentNumber(track.getWbNo());
        TRKINF data = this.fillData(delivery, track);
        JsonMapper mapper = new JsonMapper();
        try {
            this.TOOLS.sendRequestToSamsung(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }

    }

    private TRKINF fillData(PKFInfo delivery, FlippostTrack track) {
        TRKINF trackInfo = new TRKINF();
        trackInfo.setSenderIdentifier(delivery.getSenderIdentifier());
        trackInfo.setSenderIdentifier(delivery.getSenderName());
        trackInfo.setReceiverIdentifier(delivery.getReceiverIdentifier());
        trackInfo.setReceiverName(delivery.getReceiverName());
        trackInfo.setTypeIdentifier("TRKINF");
        trackInfo.setMessageName("Tracking Information");
        trackInfo.setMessageNumber("TRKINF_" + delivery.getDocumentNumber());
        trackInfo.setFunctionCode("9");
        trackInfo.setDocumentNumber(delivery.getDocumentNumber());
        trackInfo.setDocumentDate(delivery.getDocumentDate());
        trackInfo.setRelatedDocumentNumber(delivery.getRelatedDocumentNumber().getRelatedDocumentNumber());
        trackInfo.setRelatedDocumentDate(delivery.getRelatedDocumentNumber().getRelatedDocumentDate());
        trackInfo.setRelatedMessageNumber("1");

        // TODO: 27.12.2021 то за предварительные документы и откуда их взять?
        trackInfo.setRelatedMessageTypeIdentifier("OUTDLY");
        trackInfo.setTrackingType("PACK");

        // TODO: 27.12.2021 Номер отправления откуда взять?
        trackInfo.setPackingNo("1");
        trackInfo.setTotalQuantity(delivery.getTotalCargoInformation().getQuantity());
        trackInfo.setTotalQuantityCode(delivery.getTotalCargoInformation().getQuantityCode());
        trackInfo.setTotalVolume(delivery.getTotalCargoInformation().getVolume());
        trackInfo.setTotalVolumeCode(delivery.getTotalCargoInformation().getVolumeCode());
        trackInfo.setTotalGrossWeight(delivery.getTotalCargoInformation().getGrossWeight());
        trackInfo.setTotalGrossWeightCode(delivery.getTotalCargoInformation().getGrossWeightCode());
        trackInfo.setTotalChargeableWeight(delivery.getTotalCargoInformation().getChargeableWeight());
        trackInfo.setTotalChargeableWeightCode(delivery.getTotalCargoInformation().getChargeableWeightCode());
        trackInfo.setMatInfo(this.getMats(delivery, track));
        trackInfo.setEvnInfo(this.getEvent(delivery, track));

        return trackInfo;
    }

    private List<EvnList> getEvent(PKFInfo delivery, FlippostTrack track) {
        EvnList evnList = new EvnList();

        // TODO: 27.12.2021 Время прибытия курьера?
        evnList.setEstimateDate(delivery.getEstimateDateOfArrival());
        evnList.setEstimateTime(delivery.getEstimateDateOfArrival());
        evnList.setActualDate(new Date());
        evnList.setActualTime(new Date());
        evnList.setDescription(track.getDescription());
        evnList.setCurrentEvent("X");

        // TODO: 27.12.2021 Как брать города?
        return null;
    }

    private List<MatList> getMats(PKFInfo delivery, FlippostTrack track) {
        AtomicInteger itemNumber = new AtomicInteger(1);
        return delivery.getMatItems().parallelStream().map(mat -> {
            MatList item = new MatList();
            item.setSequenceNumber(mat.getSequenceNumber());
            item.setMaterial(mat.getMaterial().getMaterial());
            item.setGoodsDescription(track.getDescription());
            item.setItemNumber(itemNumber.getAndIncrement());
            item.setQuantity(mat.getCargoInformation().getQuantity());
            item.setQuantityCode(mat.getCargoInformation().getQuantityCode());
            item.setGrossWeight(mat.getCargoInformation().getGrossWeight());
            item.setGrossWeightCode(mat.getCargoInformation().getGrossWeightCode());
            item.setChargeableWeight(mat.getCargoInformation().getChargeableWeight());
            item.setChargeableWeightCode(mat.getCargoInformation().getChargeableWeightCode());
            return item;
        }).collect(Collectors.toList());
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        this.processNewTracks();
    }


}
