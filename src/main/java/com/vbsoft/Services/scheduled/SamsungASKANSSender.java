package com.vbsoft.Services.scheduled;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.vbsoft.Modeles.In.PKFInfo;
import com.vbsoft.Modeles.Out.ACKANSD.ACKANSDelivery;
import com.vbsoft.Modeles.Repositiries.DeliveryDAO;
import com.vbsoft.Utils.SamsungLogger;
import com.vbsoft.Utils.SamsungTools;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Посылает ASKANS на сервера Samsung.
 *
 * !!! Запланированное задание !!!.
 * Для изменения времени выполнения задания требуется
 * 1) Открыть файл "Constants.properties"
 * 2) Найти константу "samsung.askans.rate"
 * 3) Изменить значение
 *
 */
@Component
@PropertySources({
        @PropertySource(value = "file:${base.path}/config/Constants.properties", ignoreResourceNotFound = true),
        @PropertySource("classpath:Constants.properties")
})
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SamsungASKANSSender {

    /**
     * Контекст Spring.
     */
    ApplicationContext CONTEXT;

    SamsungLogger log;

    /**
     * Конструктор.
     * @param context Контекст Spring.
     */
    @Autowired
    public SamsungASKANSSender(ApplicationContext context) {
        CONTEXT = context;
        this.log = context.getBean(SamsungLogger.class);
    }

    /**
     * Метод выполнения задания.
     *
     * Принцип работы:
     * 1) Находит записи с полем "askansSend" равным "False" и не равным "Null".
     * 2) Отправляет запросы на сервер Samsung.
     *
     * Исключения:
     * 1) При ошибке парсинга JSON. Записывает ошибку в БД.
     * 2) Если Samsung выдает ответ отличным от "200". Записывает ошибку в БД.
     * 3) Не обработанные ошибки. Помечает "Необработанная ошибка.". Записывает ошибку в БД.
     *
     * !!! При каждой ошибки флаг "askansSend" не будет выставлен и на следующей итерации запрос будет повторен !!!
     *
     */
    @Scheduled(fixedRateString = "${samsung.askans.rate}")
    public void sendASKANS() {
        log.info("Посылаю запрос на сервер Samsung");
        List<PKFInfo> deliveries = this.getNotSentDeliveries();
        deliveries.parallelStream().forEach(this::sendASKAN);
        log.info("Запрос выслан");

    }

    /**
     * Метод засылки запроса на сервер Samsung.
     * @param REQUEST_BODY Информация о заказе
     */
    public void sendASKAN(PKFInfo REQUEST_BODY) {
        log.info("ASKANS запрос для заказа - " + REQUEST_BODY.getDocumentNumber());
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
        try {
            int code = this.CONTEXT.getBean(SamsungTools.class).sendRequestToSamsung(mapper.writeValueAsString(answer));
            if(code == 200) {
                log.info("Выставление флага отправлено для заказа - {}", REQUEST_BODY.getDocumentNumber());
                REQUEST_BODY.setAskansSend(true);
                REQUEST_BODY.setProcessedDate(new Date());
            } else {
                if(REQUEST_BODY.getTryCount() == 0) {
                    log.error("Не удалось отправить ASKANS для заказа {} ответ от сервера - {}", REQUEST_BODY.getDocumentNumber(), code);
                }
                    Integer count = REQUEST_BODY.getTryCount() - 1;
                    REQUEST_BODY.setTryCount(count);
            }
            this.CONTEXT.getBean(DeliveryDAO.class).save(REQUEST_BODY);
        } catch (JsonProcessingException json) {
            log.error("Не удалось отправить ASKANS для заказа - " + REQUEST_BODY.getDocumentNumber(), json);
        } catch (Exception ex) {
            log.error("Не удалось отправить ASKANS для заказа - " + REQUEST_BODY.getDocumentNumber() + ". Необработанная ошибка.", ex);
        }

        log.info("Запрос отправлен");
    }

    /**
     * Получает информацию о не отправленных заказах.
     * @return Информация о заказе
     */
    private List<PKFInfo> getNotSentDeliveries() {
        log.info("Формирование списка не отправленных заказов");
        DeliveryDAO dao = CONTEXT.getBean(DeliveryDAO.class);
        List<PKFInfo> deliveries = dao.findByAskansSendIsFalseAndAskansSendIsNotNullAndTryCountIsNotNullAndTryCountIsNot(-1);
        log.info("Список сформирован. Найдено заказов - '{}'", deliveries.size());
        return deliveries;
    }
}
