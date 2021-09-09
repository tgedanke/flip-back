package com.vbsoft.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.vbsoft.Modeles.In.PKFInfo;
import com.vbsoft.Modeles.Out.ACKANSD.ACKANSDelivery;
import com.vbsoft.Modeles.Out.GENRES.GENRESDelivery;
import com.vbsoft.Modeles.Out.GENRES.GENRESInfoListDelivery;
import com.vbsoft.Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SamsungDeliveryService {


    public void saveDeliveryToFile(final PKFInfo REQUEST_BODY) throws IOException {
        XmlMapper mapper = new XmlMapper();
        String fileName = "/%s_SAMSUNG.xml".formatted(new SimpleDateFormat("ddMMyyyy_HHmmSS").format(new Date()));
        String outputDir = (String) System.getProperties().getOrDefault(
                "user.dir",
                Files.createTempDirectory("Request").toFile().getAbsolutePath());
        mapper.writeValue(Paths.get(outputDir + fileName).toFile(), REQUEST_BODY);
    }

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
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(REQUEST_BODY);
        t.commit();
        session.clear();
    }

    public String sendSuccessMessage(final PKFInfo REQUEST_BODY) {
            XmlMapper mapper = new XmlMapper();
            try {
                ACKANSDelivery answer = new ACKANSDelivery();
                answer.setSenderIdentifier(REQUEST_BODY.getSenderIdentifier());
                answer.setReceiverIdentifier(REQUEST_BODY.getReceiverIdentifier());
                answer.setNumber(REQUEST_BODY.getNumber());
                answer.setMessageReceiveDate(new Date());
                answer.setMessageReceiveTime(new Date());
                answer.setAckSendDate(new Date());
                answer.setAckSendTime(new Date());
                answer.setInfo("SUCCESS");
                String xml = mapper.writeValueAsString(answer);
                return xml;
            } catch (JsonProcessingException e) {
                return this.sendErrorMessage(REQUEST_BODY, e.getMessage());
            }
    }

    public String sendErrorMessage(final PKFInfo REQUEST_BODY, final String MESSAGE) {
        GENRESDelivery model = new GENRESDelivery();
        model.setMessageSenderIdentifier(REQUEST_BODY.getSenderIdentifier());
        model.setMessageSenderName(REQUEST_BODY.getSenderName());
        model.setReceiverIdentifier(REQUEST_BODY.getReceiverIdentifier());
        model.setMessageSenderName(REQUEST_BODY.getReceiverName());
        // TODO: 25.08.2021 Как формируется номер сообщения
        model.setMessageNumber("aaaa");
        model.setDocumentNumber(REQUEST_BODY.getDocumentNumber());
        model.setDocumentDate(REQUEST_BODY.getDocumentDate());
        // TODO: 25.08.2021 Подумать что такое предварительный документ и его номер
        model.setReferenceMessageNumber("aaa");
        model.setReferenceDocumentNumber("bbb");
        GENRESInfoListDelivery info = new GENRESInfoListDelivery();
        info.setGenres(model);
        info.setSequenceNumber(1);
        info.setInformation(MESSAGE);
        model.getInfo().add(info);

        XmlMapper mapper = new XmlMapper();
        try {
            return mapper.writeValueAsString(info);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "GLOBAL ERROR";
        }
    }


}
