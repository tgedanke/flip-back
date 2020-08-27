package com.flippost.DAO.Samsung.NewDelivery.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "nr1:PKGINF")
public class SamsungNewDelivery {

    private int id;

    @JacksonXmlProperty(localName = "MessageSenderIdentifier")
    private String messageSenderIdentifier;

    @JacksonXmlProperty(localName = "MessageSenderName")
    private String messageSenderName;

    @JacksonXmlProperty(localName = "MessageReceiverIdentifier")
    private String messageReceiverIdentifier;

    @JacksonXmlProperty(localName = "MessageReceiverName")
    private String messageReceiverName;

    @JacksonXmlProperty(localName = "MessageTypeIdentifier")
    private String messageTypeIdentifier;

    @JacksonXmlProperty(localName = "MessageName")
    private String messageName;

    @JacksonXmlProperty(localName = "MessageNumber")
    private String messageNumber;

    @JacksonXmlProperty(localName = "MessageFunctionCode")
    private String messageFunctionCode;

    @JacksonXmlProperty(localName = "DocumentNumber")
    private String documentNumber;

    @JacksonXmlProperty(localName = "DocumentDate")
    private String documentDate;


    @JacksonXmlProperty(localName = "BusinessType")
    private SamsungBusinessType businessType;

    @JacksonXmlProperty(localName = "OrderType")
    private SamsungOrderType orderType;

    @JacksonXmlProperty(localName = "ServiceType")
    private SamsungServiceType serviceType;

    @JacksonXmlProperty(localName = "Division")
    private SamsungDivision division;

    @JacksonXmlProperty(localName = "RelatedDocument")
    private SamsungRelatedDocument relatedDocument;

    @JacksonXmlProperty(localName = "RelatedDocumentNumber")
    private SamsungRelatedDocumentNumber relatedDocumentNumber;

    @JacksonXmlProperty(localName = "TransportationType")
    private SamsungTransportationType samsungTransportationType;

    @JacksonXmlProperty(localName = "TotalCargoInformation")
    private SamsungTotalCargoInformation samsungTotalCargoInformation;

    @JacksonXmlElementWrapper(localName = "OrgList")
    @JacksonXmlProperty(localName = "OrgItem")
    Set<SamsungOrgItem> OrgItem = new HashSet<>();
}

