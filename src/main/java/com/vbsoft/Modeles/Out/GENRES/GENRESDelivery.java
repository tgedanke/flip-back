package com.vbsoft.Modeles.Out.GENRES;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "GENRESDelivery")
@Data
@XmlRootElement(name = "nr1:GENRES")
public class GENRESDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long ID;

    @Transient
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @JacksonXmlProperty(localName = "xmlns:nr1", isAttribute = true)
    private String ins = "http://edi.sec.samsung.com/GLS_ECC_LE/TLEM";

    @Transient
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @JacksonXmlProperty(localName = "xmlns:xsi", isAttribute = true)
    private String xsi = "http://www.w3.org/2001/XMLSchema-instance";

    @Transient
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @JacksonXmlProperty(localName = "xsi:schemaLocation", isAttribute = true)
    private String schemaLocation = "http://edi.sec.samsung.com/GLS_ECC_LE/TLEM\n" +
            "http://edi.sec.samsung.com/GLS_ECC_LE/TLEM/GENRES.xsd";


   @JacksonXmlProperty(localName = "MessageSenderIdentifier")
   private String messageSenderIdentifier;

   @JacksonXmlProperty(localName = "MessageSenderName")
   private String messageSenderName;

    @JacksonXmlProperty(localName = "MessageReceiverIdentifier")
    private String receiverIdentifier;

    @JacksonXmlProperty(localName = "MessageReceiverName")
    private String receiverName;

    @JacksonXmlProperty(localName = "MessageTypeIdentifier")
    private String typeIdentifier = "GENRES";

    @JacksonXmlProperty(localName = "MessageName")
    private String messageName = "General Reponse";

    @JacksonXmlProperty(localName = "MessageNumber")
    private String messageNumber;

    @JacksonXmlProperty(localName = "MessageFunctionCode")
    private String functionCode;

    @JacksonXmlProperty(localName = "DocumentNumber")
    private String documentNumber;

    @JacksonXmlProperty(localName = "DocumentDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private Date documentDate;

    @JacksonXmlProperty(localName = "ReferenceMessageTypeIdentifier")
    private String referenceMessageTypeIdentifier = "SHPINF";

    @JacksonXmlProperty(localName = "ReferenceDocumentNumber")
    private String referenceDocumentNumber;

    @JacksonXmlProperty(localName = "ReferenceMessageNumber")
    private String referenceMessageNumber;

    @JacksonXmlElementWrapper(localName = "InfoList")
    @JacksonXmlProperty(localName = "InfoItem")
    @OneToMany(mappedBy = "genres", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GENRESInfoListDelivery> info = new LinkedList<>();
}
