package com.vbsoft.Exceptions;

import com.vbsoft.Modeles.In.PKFInfo;
import com.vbsoft.Modeles.Out.GENRES.GENRESDelivery;
import com.vbsoft.Modeles.Out.GENRES.GENRESInfoListDelivery;
import lombok.Getter;

/**
 * Service exceptions.
 *
 * @author vd.zinovev
 * @version 1.0
 * @since 1.0
 *
 */
public class ServiceException extends Exception {

    /**
     * Информация о доставке.
     */
    @Getter
    private final PKFInfo info;

    @Getter
    private final GENRESDelivery model;

    /**
     * Конструктор исключения.
     * @param REQUEST_BODY Текущая информация о доставке
     * @param MESSAGE Возникшее исключение
     */
    public ServiceException(PKFInfo REQUEST_BODY,  String MESSAGE) {
        super(MESSAGE);
        this.info = REQUEST_BODY;

        this.model = new GENRESDelivery();
        model.setMessageSenderIdentifier(REQUEST_BODY.getSenderIdentifier());
        model.setMessageSenderName(REQUEST_BODY.getSenderName());
        model.setReceiverIdentifier(REQUEST_BODY.getReceiverIdentifier());
        model.setMessageSenderName(REQUEST_BODY.getReceiverName());
        // TODO: 25.08.2021 Как формируется номер сообщения
        model.setMessageNumber("a");
        model.setDocumentNumber(REQUEST_BODY.getDocumentNumber());
        model.setDocumentDate(REQUEST_BODY.getDocumentDate());
        // TODO: 25.08.2021 Подумать что такое предварительный документ и его номер
        model.setReferenceMessageNumber("a");
        model.setReferenceDocumentNumber("b");
        GENRESInfoListDelivery info = new GENRESInfoListDelivery();
        info.setGenres(model);
        info.setSequenceNumber(1);
        info.setInformation(MESSAGE);
        model.getInfo().add(info);
    }

}
