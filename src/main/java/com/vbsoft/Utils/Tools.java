package com.vbsoft.Utils;

import lombok.extern.log4j.Log4j2;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Objects;

/**
 * Инструменты сервиса.
 *
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 */
@Component
@PropertySource("classpath:Constants.properties")
@Log4j2
public final class Tools {

    private final Environment environment;

    @Value("${samsung.send.host}")
    private String samsungHost;

    @Value("${samsung.send.host.prod}")
    private String samsungProdHost;

    @Autowired
    public Tools(Environment environment) {
        this.environment = environment;
    }

    /**
     * Отправка ответа обработки заказа на сервер самсунга.
     * @param REQUEST_BODY Тела ответа
     */
    public void sendRequestToSamsung(String REQUEST_BODY) {
        OkHttpClient client = new OkHttpClient();
        OkHttpClient.Builder builder = client.newBuilder();
        builder.sslSocketFactory(trustAllSslSocketFactory, (X509TrustManager) trustAllCerts[0]);
        builder.hostnameVerifier((hostname, session) -> true);
        client = builder.build();
        String currentURL = this.environment.getActiveProfiles()[0].equalsIgnoreCase("prod") ? this.samsungProdHost: this.samsungHost;
        REQUEST_BODY = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + REQUEST_BODY;
        RequestBody body = RequestBody.create(MediaType.parse("text/xml"), REQUEST_BODY);
        Request request = new Request.Builder().url(currentURL).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            log.info("""
                            Ответ на сервер самсунга отправлен.
                            Url - '%s'.
                            Код ответа - '%d'
                            
                            Тело ответа:
                             '%s'
                            """.formatted(
                    currentURL,
                    response.code(),
                    response.code()));
            if(response.code() != 200) {
                log.error("""
                            Ответ от сервера самсунг отличается от ожидаемого.
                            Url - '%s'.
                            Код ответа - '%d'
                            Ожидаемый код - '200'
                            """.formatted(
                        this.samsungHost,
                        response.code()));
            }
            log.info(Objects.requireNonNull(response.body()).string());
        } catch (IOException e) {
            log.error("""
                            Ошибка чтения ответа от сервера самсунг.
                            Url - '%s'.
                            Сообщение - '%s'
                            """.formatted(
                    this.samsungHost,
                    e.getMessage()));
        } catch (Exception ex) {
            log.error("""
                            Необработанная ошибка при посылки запроса на сервер самсунг.
                            Url - '%s'.
                            Сообщение - '%s'
                            """.formatted(
                    this.samsungHost,
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
