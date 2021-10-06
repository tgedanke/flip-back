package com.vbsoft.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.vbsoft.Modeles.In.PKFInfo;
import com.vbsoft.Modeles.Out.ACKANSD.ACKANSDelivery;
import com.vbsoft.Utils.HibernateUtils;
import okhttp3.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class SamsungDeliveryService {

    private final String URL = "112.106.130.24:29443";
    private final Logger LOG = Logger.getLogger("Root");

    public void saveDeliveryToFile(final PKFInfo REQUEST_BODY) throws IOException {
        XmlMapper mapper = new XmlMapper();
        String fileName = "/%s_SAMSUNG.xml".formatted(new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date()));
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

    public String sendSuccessMessage(final PKFInfo REQUEST_BODY) throws JsonProcessingException {
        XmlMapper mapper = new XmlMapper();
        ACKANSDelivery answer = new ACKANSDelivery();
        answer.setSenderIdentifier(REQUEST_BODY.getSenderIdentifier());
        answer.setReceiverIdentifier(REQUEST_BODY.getReceiverIdentifier());
        answer.setNumber(REQUEST_BODY.getNumber());
        answer.setMessageReceiveDate(new Date());
        answer.setMessageReceiveTime(new Date());
        answer.setAckSendDate(new Date());
        answer.setAckSendTime(new Date());
        answer.setInfo("SUCCESS");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("text/xml"), mapper.writeValueAsString(answer));
        Request request = new Request.Builder().url(URL).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            LOG.info(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
            LOG.log(Level.WARNING, e.getMessage());
        }
        return mapper.writeValueAsString(answer);
    }


}
