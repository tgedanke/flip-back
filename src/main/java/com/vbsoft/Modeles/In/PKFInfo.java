package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.vbsoft.Modeles.In.Enums.FreightMethodCode;
import com.vbsoft.Modeles.In.Enums.IncotermsCode;
import com.vbsoft.Modeles.In.Enums.MessageFunctionCode;
import com.vbsoft.Modeles.In.Enums.PaymentMethodCode;
import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Информация о новой доставке.
 * Таблица - wwwSamsungPKGInfo.
 * @version 1.0
 * @since 1.0
 * @author vd.zinovev
 */
@Entity
@Table(name = "DeliveryPKGInfo")
@Data
@XmlRootElement(name = "nr1:PKGINF")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PKFInfo implements Serializable {

    /**
     * ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long ID;

    /**
     * Аттрибут заголовка nr1:PKGINF.
     * Не добавляется в базу.
     */
    @Transient
    @JacksonXmlProperty(localName = "xmlns:nr1", isAttribute = true)
    private String nr1 = "http://edi.sec.samsung.com/GLS_ECC_LE/TLEM";

    /**
     * Аттрибут заголовка nr1:PKGINF. Тип схемы.
     * Не добавляется в базу.
     */
    @Transient
    @JacksonXmlProperty(localName = "xmlns:xsi", isAttribute = true)
    private String xsi = "http://www.w3.org/2001/XMLSchema-instance";

    /**
     * Аттрибут заголовка nr1:PKGINF. Ссылка на схемы.
     * Не добавляется в базу.
     */
    @Transient
    @JacksonXmlProperty(localName = "xsi:schemaLocation", isAttribute = true)
    private String schemaLocation = "http://edi.sec.samsung.com/GLS_ECC_LE/TLEM\n" +
            "http://edi.sec.samsung.com/GLS_ECC_LE/TLEM/PKGINF.xsd";


    /**
     * Код компанииотправителя.
     */
    @JacksonXmlProperty(localName = "MessageSenderIdentifier")
    private String senderIdentifier;

    /**
     * Наименование компании отправителя.
     */
    @JacksonXmlProperty(localName = "MessageSenderName")
    private String senderName;

    /**
     * Код компании получателя.
     */
    @JacksonXmlProperty(localName = "MessageReceiverIdentifier")
    private String receiverIdentifier;

    /**
     * Наименование компании получателя.
     */
    @JacksonXmlProperty(localName = "MessageReceiverName")
    private String receiverName;

    /**
     * Код типа сообщения.
     */
    @JacksonXmlProperty(localName = "MessageTypeIdentifier")
    private String typeIdentifier;

    /**
     * Наименование типа сообщения.
     */
    @JacksonXmlProperty(localName = "MessageName")
    private String name;

    /**
     * Уникальный идентификатор сообщения.
     */
    @JacksonXmlProperty(localName = "MessageNumber")
    private String number;

    /**
     * Код статуса отправки сообщения.
     * @see MessageFunctionCode - справочник с кодами.
     */
    @JacksonXmlProperty(localName = "MessageFunctionCode")
    private MessageFunctionCode functionCode;

    /**
     * Номер документа CELLO.
     */
    @JacksonXmlProperty(localName = "DocumentNumber")
    private String documentNumber;

    /**
     * Дата документа.
     */
    @JacksonXmlProperty(localName = "DocumentDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private Date documentDate;

    /**
     * Описание типа процесса.
     */
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "info", orphanRemoval = true)
    @JacksonXmlProperty(localName = "BusinessType")
    private BusinessType businessType;

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
        this.businessType.setInfo(this);
    }

    /**
     * SAP Material Division.
     */
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "info", orphanRemoval = true)
    @JacksonXmlProperty(localName = "Division")
    private Division division;

    public void setDivision(Division division) {
        this.division = division;
        this.division.setInfo(this);
    }

    /**
     * Документа основания.
     */
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "info", orphanRemoval = true)
    @JacksonXmlProperty(localName = "RelatedDocument")
    private RelatedDocument relatedDocument;

    public void setRelatedDocument(RelatedDocument relatedDocument) {
        this.relatedDocument = relatedDocument;
        this.relatedDocument.setInfo(this);
    }

    /**
     * Номер документа основания.
     */
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "info", orphanRemoval = true)
    @JoinColumn(name = "RelatedDocumentNumber")
    @JacksonXmlProperty(localName = "RelatedDocumentNumber")
    private RelatedDocumentNumber relatedDocumentNumber;

    public void setRelatedDocumentNumber(RelatedDocumentNumber relatedDocumentNumber) {
        this.relatedDocumentNumber = relatedDocumentNumber;
        this.relatedDocumentNumber.setInfo(this);
    }

    /**
     * Тип доставки (структура).
     */
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "info", orphanRemoval = true)
    @JacksonXmlProperty(localName = "TransportationType")
    private TransportationType transportationType;

    public void setTransportationType(TransportationType transportationType) {
        this.transportationType = transportationType;
        this.transportationType.setInfo(this);
    }

    /**
     * Название транспортного средства 1.
     */
    @JacksonXmlProperty(localName = "VesselFlightTruckName1")
    private String vesselFlightTruckName1;

    /**
     * Название транспортного средства 2.
     */
    @JacksonXmlProperty(localName = "VesselFlightTruckName2")
    private String vesselFlightTruckName2;

    /**
     * INCOTERMS Code.
     */
    @JacksonXmlProperty(localName = "IncotermsCode")
    private IncotermsCode incotermsCode;

    /**
     * INCOTERMS Place.
     */
    @JacksonXmlProperty(localName = "IncotermsPlace")
    private String incotermsPlace;

    /**
     * Тип оплаты.
     */
    @JacksonXmlProperty(localName = "FreightMethod")
    private FreightMethodCode freightMethod;

    /**
     * Форма оплаты 1.
     */
    @JacksonXmlProperty(localName = "PaymentMethod1")
    private PaymentMethodCode paymentMethod1 = PaymentMethodCode.Cheque;

    /**
     * Форма оплаты 2.
     */
    @JacksonXmlProperty(localName = "PaymentMethod2")
    private PaymentMethodCode paymentMethod2 = PaymentMethodCode.Cash;

    /**
     * Форма оплаты 3.
     */
    @JacksonXmlProperty(localName = "PaymentMethod3")
    private PaymentMethodCode paymentMethod3;

    /**
     * Форма оплаты 4.
     */
    @JacksonXmlProperty(localName = "PaymentMethod4")
    private PaymentMethodCode paymentMethod4;

    /**
     * Информация о сроке оплаты.
     */
    @JacksonXmlProperty(localName = "PaymentPeriod1")
    private String paymentPeriod1;

    /**
     * Информация о сроке оплаты 2.
     */
    @JacksonXmlProperty(localName = "PaymentPeriod2")
    private String paymentPeriod2;

    /**
     * Комиссия.
     */
    @JacksonXmlProperty(localName = "Commission")
    private String commission;

    /**
     * Банк.
     */
    @JacksonXmlProperty(localName = "LCIssueBank")
    private String LCIssueBank;

    /**
     * Дата отгрузки в формате ГГГГММДД.
     */
    @JacksonXmlProperty(localName = "ShippingDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private Date shippingDate;

    /**
     * Время отгрузки в формате ГГГГММДД.
     */
    @JacksonXmlProperty(localName = "ShippingTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HHmmss")
    private Date shippingTime;

    /**
     * Ожидаемая дата доставки в формате ГГГГММДД.
     */
    @JacksonXmlProperty(localName = "EstimateDateofArrival")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private Date estimateDateOfArrival;

    /**
     * Суммарная информация о доставке.
     */
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "info", orphanRemoval = true)
    @JacksonXmlProperty(localName = "TotalCargoInformation")
    private TotalCargoInformation totalCargoInformation;

    public void setTotalCargoInformation(TotalCargoInformation totalCargoInformation) {
        if(totalCargoInformation != null) {
            this.totalCargoInformation = totalCargoInformation;
            this.totalCargoInformation.setInfo(this);
        }
    }

    /**
     * Список получателей.
     */
    @JacksonXmlElementWrapper(localName = "OrgList")
    @JacksonXmlProperty(localName = "OrgItem")
    @OneToMany(mappedBy = "info", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = OrgItem.class)
    private List<OrgItem> orgItems = new LinkedList<>();

    /**
     * Запись Список получателей.
     * @param orgItems Список получателей
     */
    public void setOrgItems(List<OrgItem> orgItems) {
        if(orgItems != null) {
            orgItems.forEach(item -> item.setInfo(this));
            this.orgItems = orgItems;
        }
    }

    /**
     * Информация об отправителе и маршруте.
     */
    @JacksonXmlElementWrapper(localName = "LocList")
    @JacksonXmlProperty(localName = "LocItem")
    @OneToMany(mappedBy = "info", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LocItem> locItems = new LinkedList<>();

    public void setLocItems(List<LocItem> locItems) {
        if(locItems != null) {
            this.locItems = locItems;
            this.locItems.forEach(item -> item.setInfo(this));
        }
    }

    @JacksonXmlElementWrapper(localName = "RefList")
    @JacksonXmlProperty(localName = "RefItem")
    @OneToMany(mappedBy = "info", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RefItem> refItems = new LinkedList<>();

    public void setRefItems(List<RefItem> refItems) {
        if(refItems != null) {
            this.refItems.forEach(item -> item.setInfo(this));
            this.refItems = refItems;
        }
    }

    @JacksonXmlElementWrapper(localName = "MatList")
    @JacksonXmlProperty(localName = "MatItem")
    @OneToMany(mappedBy = "info", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MatItem> matItems = new LinkedList<>();

    public void setMatItems(List<MatItem> matItems) {
        if(matItems != null) {
            this.matItems = matItems;
            this.pkgItems.forEach(pkgItem -> pkgItem.setInfo(this));
        }
    }

    @JacksonXmlElementWrapper(localName = "PkgList")
    @JacksonXmlProperty(localName = "PkgItem")
    @OneToMany(mappedBy = "info", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PkgItem> pkgItems = new LinkedList<>();


    public void setPkgItems(List<PkgItem> pkgItems) {
        if(pkgItems != null) {
            this.pkgItems = pkgItems;
            this.pkgItems.forEach(item -> item.setInfo(this));
        }
    }
}
