package com.vbsoft.Modeles.Out.ACKANSD;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.vbsoft.Modeles.In.PKFInfo;
import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Entity
@Table(name = "ACKANSDeliveries")
@Data
@XmlRootElement(name = "nr1:ACKANS")
@JsonRootName("nr1:ACKANS")
public class ACKANSDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long ID;

    @JacksonXmlProperty(localName = "xmlns:nr1", isAttribute = true)
    private String ns = "http://edi.sec.samsung.com/GLS_ECC_LE/ULEM";

    @JacksonXmlProperty(localName = "MessageSenderIdentifier")
    private String senderIdentifier;
    @JacksonXmlProperty(localName = "MessageReceiverIdentifier")
    private String receiverIdentifier;
    @JacksonXmlProperty(localName = "MessageTypeIdentifier")
    private String typeIdentifier = "ACKANS";
    @JacksonXmlProperty(localName = "MessageFunctionCode")
    private String functionCode = "S";
    @JacksonXmlProperty(localName = "ReferenceMessageTypeIdentifier")
    private String referenceMessageTypeIdentifier = "PKGINF";
    @JacksonXmlProperty(localName = "MessageNumber")
    private String number;
    @JacksonXmlProperty(localName = "MessageReceiveDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private Date messageReceiveDate;

    @JacksonXmlProperty(localName = "MessageReceiveTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HHmmss")
    private Date messageReceiveTime;
    @JacksonXmlProperty(localName = "AckSendDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private Date ackSendDate;

    @JacksonXmlProperty(localName = "AckSendTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HHmmss")
    private Date ackSendTime;

    @JacksonXmlProperty(localName = "HandlingInformation")
    private String info;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pkfInfo")
    @JsonIgnore
    private PKFInfo pkfInfo;
}
