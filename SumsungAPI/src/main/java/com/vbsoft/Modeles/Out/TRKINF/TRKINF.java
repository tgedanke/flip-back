package com.vbsoft.Modeles.Out.TRKINF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.vbsoft.Modeles.Out.GENRES.GENRESInfoListDelivery;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TRKINF")
@Data
@XmlRootElement(name = "nr1:TRKINF")
public class TRKINF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long ID;

    @Transient
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @JacksonXmlProperty(localName = "xmlns:nr1", isAttribute = true)
    private final String ins = "http://edi.sec.samsung.com/GLS_ECC_LE/ALEM";

    @Transient
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @JacksonXmlProperty(localName = "xmlns:nr1", isAttribute = true)
    private final String xsi = "http://www.w3.org/2001/XMLSchema-instance";

    @Transient
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @JacksonXmlProperty(localName = "xsi:schemaLocation", isAttribute = true)
    private String schemaLocation = "http://edi.sec.samsung.com/GLS_ECC_LE/ALEM TRKINF.xsd";

    @JacksonXmlProperty(localName = "MessageSenderIdentifier")
    private String messageIdentifier;

    @JacksonXmlProperty(localName = "MessageSenderName")
    private String senderIdentifier;

    @JacksonXmlProperty(localName = "MessageReceiverIdentifier")
    private String receiverIdentifier;

    @JacksonXmlProperty(localName = "MessageReceiverName")
    private String receiverName;

    @JacksonXmlProperty(localName = "MessageTypeIdentifier")
    private String typeIdentifier = "TRKINF";

    @JacksonXmlProperty(localName = "MessageName")
    private String messageName = "Tracking Information<";

    @JacksonXmlProperty(localName = "MessageNumber")
    private String messageNumber;

    @JacksonXmlProperty(localName = "MessageFunctionCode")
    private String functionCode;

    @JacksonXmlProperty(localName = "DocumentNumber")
    private String documentNumber;

    @JacksonXmlProperty(localName = "DocumentDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private Date documentDate;

    @JacksonXmlProperty(localName = "RelatedMessageTypeIdentifier")
    private String relatedMessageTypeIdentifier = "OUTDLY";

    @JacksonXmlProperty(localName = "RelatedMessageNumber")
    private String relatedMessageNumber;

    @JacksonXmlProperty(localName = "RelatedDocumentNumber")
    private String relatedDocumentNumber;

    @JacksonXmlProperty(localName = "RelatedDocumentDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private Date relatedDocumentDate;

    @JacksonXmlProperty(localName = "TrackingType")
    private String trackingType;

    @JacksonXmlProperty(localName = "PackingNo")
    private String packingNo;

    @JacksonXmlProperty(localName = "TotalQuantity")
    private int totalQuantity;

    @JacksonXmlProperty(localName = "TotalQuantityCode")
    private String totalQuantityCode;

    @JacksonXmlProperty(localName = "TotalVolume")
    private float totalVolume;

    @JacksonXmlProperty(localName = "TotalVolumeCode")
    private String totalVolumeCode = "M3";

    @JacksonXmlProperty(localName = "TotalGrossWeight")
    private float totalGrossWeight;

    @JacksonXmlProperty(localName = "TotalGrossWeightCode")
    private String totalGrossWeightCode = "KG";

    @JacksonXmlProperty(localName = "TotalChargeableWeight")
    private float totalChargeableWeight;

    @JacksonXmlProperty(localName = "TotalChargeableWeightCode")
    private String totalChargeableWeightCode = "KG";

    @JacksonXmlElementWrapper(localName = "EvnList")
    @JacksonXmlProperty(localName = "EvnItem")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "info", orphanRemoval = true)
    private List<GENRESInfoListDelivery> evnInfo;

    @JacksonXmlElementWrapper(localName = "MatList")
    @JacksonXmlProperty(localName = "MatItem")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "info", orphanRemoval = true)
    private List<GENRESInfoListDelivery> matInfo;
}
