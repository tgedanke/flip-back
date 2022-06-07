package com.vbsoft.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.vbsoft.Modeles.In.PKFInfo;
import com.vbsoft.Modeles.Out.ACKANSD.ACKANSDelivery;
import com.vbsoft.Modeles.Out.GENRES.GENRESDelivery;
import com.vbsoft.Modeles.Out.GENRES.GENRESInfoListDelivery;
import com.vbsoft.Modeles.Repositiries.DeliveryDAO;
import com.vbsoft.Utils.Tools;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.util.FileUtil;
import org.h2.store.fs.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SamsungDeliveryService {

    /**
     * Delivery dao.
     */
    private final DeliveryDAO DELIVERY_DAO;


    /**
     * Service tools.
     */
    private final Tools TOOLS;

    /**
     * Samsung answer server url.
     */
    @Getter
    private final String URL = "https://korealogistics.samsungedi.com:9443/http/handler?SenderCode=lsp_flippost";

    /**
     * Samsung answer server url.
     */
    @Getter
    private final String URL_TEST = "https://koreatest.samsungedi.com:19443/http/handler?SenderCode=lsp_flippost";

    /**
     * Service constructor.
     * @param deliveryDAO Delivery dao
     * @param tools Service tools
     */
    @Autowired
    public SamsungDeliveryService(DeliveryDAO deliveryDAO, Tools tools) {
        this.DELIVERY_DAO = deliveryDAO;
        this.TOOLS = tools;
    }

    /**
     * Get package item by document number.
     * @param number Document number
     * @return Package item
     */
    public PKFInfo getRequestByDocumentNumber(String number) {
        List<PKFInfo> infoList = this.DELIVERY_DAO.findByDocumentNumber(number).parallelStream().sorted(Comparator.comparing(PKFInfo::getDocumentDate)).collect(Collectors.toList());
        return infoList.get(infoList.size() - 1);
    }

    /**
     * Save sumsung request to file.
     * @param REQUEST_BODY Package item
     * @throws IOException Throws file read exception
     */
    public void saveDeliveryToFile(final String REQUEST_BODY) throws IOException {
        String fileName = "/%s_SAMSUNG.xml".formatted(new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date()));
        String outputDir = (String) System.getProperties().getOrDefault(
                "user.dir",
                Files.createTempDirectory("Request").toFile().getAbsolutePath());
        FileUtil.writeAsString(Paths.get(outputDir + fileName).toFile(), REQUEST_BODY);
    }


    /**
     * Save sumsung request to file.
     * @param REQUEST_BODY Package item
     * @throws IOException Throws file read exception
     */
    public void saveDeliveryToFile(final PKFInfo REQUEST_BODY) throws IOException {
        XmlMapper mapper = new XmlMapper();
        String fileName = "/%s_SAMSUNG.xml".formatted(new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date()));
        String outputDir = (String) System.getProperties().getOrDefault(
                "user.dir",
                Files.createTempDirectory("Request").toFile().getAbsolutePath());
        mapper.writeValue(Paths.get(outputDir + fileName).toFile(), REQUEST_BODY);
    }

    /**
     * Save package item to DB.
     * @param REQUEST_BODY Package item
     */
    public void saveSamsungRequest(final PKFInfo REQUEST_BODY) {
        REQUEST_BODY.getBusinessType().setInfo(REQUEST_BODY);
        REQUEST_BODY.getDivision().setInfo(REQUEST_BODY);
        REQUEST_BODY.getRelatedDocument().setInfo(REQUEST_BODY);
        REQUEST_BODY.getRelatedDocumentNumber().setInfo(REQUEST_BODY);
        REQUEST_BODY.getTransportationType().setInfo(REQUEST_BODY);
        REQUEST_BODY.getTotalCargoInformation().setInfo(REQUEST_BODY);
        REQUEST_BODY.getOrgItems().forEach(item -> item.setInfo(REQUEST_BODY));
        REQUEST_BODY.getLocItems().forEach(item -> item.setInfo(REQUEST_BODY));
        REQUEST_BODY.getRefItems().forEach(item -> item.setInfo(REQUEST_BODY));
        REQUEST_BODY.getMatItems().forEach(item -> item.setInfo(REQUEST_BODY));
        REQUEST_BODY.getPkgItems().forEach(item -> item.setInfo(REQUEST_BODY));
        DELIVERY_DAO.save(REQUEST_BODY);
    }

    /**
     * Return success answer.
     * @param REQUEST_BODY Package item
     * @return ACKANS
     * @throws JsonProcessingException Throws JSON formatting exception
     */
    public String sendSuccessMessage(final PKFInfo REQUEST_BODY) throws JsonProcessingException {
        XmlMapper mapper = new XmlMapper();
        ACKANSDelivery answer = new ACKANSDelivery();
        answer.setSenderIdentifier(REQUEST_BODY.getReceiverIdentifier());
        answer.setReceiverIdentifier(REQUEST_BODY.getSenderIdentifier());
        answer.setNumber(REQUEST_BODY.getNumber());
        answer.setMessageReceiveDate(new Date());
        answer.setMessageReceiveTime(new Date());
        answer.setAckSendDate(new Date());
        answer.setAckSendTime(new Date());
        answer.setInfo("SUCCESS");
        this.TOOLS.sendRequestToSamsung(mapper.writeValueAsString(answer));

        return mapper.writeValueAsString(answer);
    }

    public void sendErrorMessage(final PKFInfo REQUEST_BODY, final String MESSAGE) {
        GENRESDelivery model = new GENRESDelivery();
        model.setMessageSenderIdentifier(REQUEST_BODY.getSenderIdentifier());
        model.setMessageSenderName(REQUEST_BODY.getSenderName());
        model.setReceiverIdentifier(REQUEST_BODY.getReceiverIdentifier());
        model.setMessageSenderName(REQUEST_BODY.getReceiverName());
        model.setMessageNumber(REQUEST_BODY.getNumber());
        model.setDocumentNumber(REQUEST_BODY.getDocumentNumber());
        model.setDocumentDate(REQUEST_BODY.getDocumentDate());
        model.setReferenceMessageNumber(REQUEST_BODY.getDocumentNumber());
        model.setReferenceDocumentNumber(REQUEST_BODY.getDocumentNumber());
        GENRESInfoListDelivery info = new GENRESInfoListDelivery();
        info.setGenres(model);
        info.setSequenceNumber(1);
        info.setInformation(MESSAGE);
        model.getInfo().add(info);
    }
}
