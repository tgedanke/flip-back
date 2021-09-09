package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DeliveryRelatedDocumentNumber")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RelatedDocumentNumber {

    /**
     * ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long ID;

    /**
     * Номер сообщения документа основания.
     */
    @JacksonXmlProperty(localName = "RelatedMessageNumber")
    private String relatedMessageNumber;

    /**
     * Номер документа основания.
     */
    @JacksonXmlProperty(localName = "RelatedDocumentNumber")
    private String relatedDocumentNumber;

    /**
     * Дата документаоснования в формате ГГГГММДД.
     */
    @JacksonXmlProperty(localName = "RelatedDocumentDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private Date relatedDocumentDate;

    /**
     * Информация об отправке.
     */
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "infoID")
    private PKFInfo info;
}

