package com.vbsoft.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.vbsoft.Modeles.In.PKFInfo;
import com.vbsoft.Modeles.Out.ACKANSD.ACKANSDelivery;
import com.vbsoft.Modeles.Repositiries.DeliveryDAO;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Service
@Slf4j
public class SamsungDeliveryService {

    /**
     * Delivery dao.
     */
    private final DeliveryDAO deliveryDAO;

    /**
     * Samsung answer server url.
     */
    @Getter
    private final String URL = "https://dev.samsungedi.com:29443/http/handler?SenderCode=lsp_flippost";

    /**
     * Service constructor.
     * @param deliveryDAO Delivery dao
     */
    @Autowired
    public SamsungDeliveryService(DeliveryDAO deliveryDAO) {
        this.deliveryDAO = deliveryDAO;
    }

    /**
     * Get package item by document number.
     * @param number Document number
     * @return Package item
     */
    public PKFInfo getRequestByDocumentNumber(String number) {
        return this.deliveryDAO.findByDocumentNumber(number);
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
        deliveryDAO.save(REQUEST_BODY);
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
        this.sendRequestToSamsung(mapper.writeValueAsString(answer));

        return mapper.writeValueAsString(answer);
    }

    /**
     * Отправка ответа обработки заказа на сервер самсунга.
     * @param REQUEST_BODY Тела ответа
     */
    private void sendRequestToSamsung(String REQUEST_BODY) {
        OkHttpClient client = new OkHttpClient();
        OkHttpClient.Builder builder = client.newBuilder();
        builder.sslSocketFactory(trustAllSslSocketFactory, (X509TrustManager) trustAllCerts[0]);
        builder.hostnameVerifier((hostname, session) -> true);
        client = builder.build();
        REQUEST_BODY = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + REQUEST_BODY;
        RequestBody body = RequestBody.create(MediaType.parse("text/xml"), REQUEST_BODY);
        Request request = new Request.Builder().url(URL).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            log.info("""
                            Ответ на сервер самсунга отправлен.
                            Url - '%s'.
                            Код ответа - '%d'
                            
                            Тело ответа:
                             '%s'
                            """.formatted(
                                    URL,
                            response.code(),
                            response.code()));
            if(response.code() != 200) {
                log.error("""
                            Ответ от сервера самсунг отличается от ожидаемого.
                            Url - '%s'.
                            Код ответа - '%d'
                            Ожидаемый код - '200'
                            """.formatted(
                        URL,
                        response.code()));
            }
            log.info(Objects.requireNonNull(response.body()).string());
        } catch (IOException e) {
            log.error("""
                            Ошибка чтения ответа от сервера самсунг.
                            Url - '%s'.
                            Сообщение - '%s'
                            """.formatted(
                    URL,
                    e.getMessage()));
        } catch (Exception ex) {
            log.error("""
                            Необработанная ошибка при посылки запроса на сервер самсунг.
                            Url - '%s'.
                            Сообщение - '%s'
                            """.formatted(
                    URL,
                    ex.getMessage()));
        }
    }

    private static final TrustManager[] trustAllCerts = new TrustManager[] {
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[] {};
                }
            }

    };

    private static final SSLContext trustAllSslContext;

    static {
        try {
            trustAllSslContext = SSLContext.getInstance("SSL");
            trustAllSslContext.init(null, trustAllCerts, new SecureRandom());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static final SSLSocketFactory trustAllSslSocketFactory = trustAllSslContext.getSocketFactory();

}
