package com.flippost.DAO.Samsung.NewDelivery.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SamsungRelatedDocumentNumber {

    private int id;

    @JacksonXmlProperty(localName = "RelatedMessageNumber")
    private String RelatedMessageNumber;

    @JacksonXmlProperty(localName = "RelatedDocumentNumber")
    private String RelatedDocumentNumber;

    @JacksonXmlProperty(localName = "RelatedDocumentDate")
    private String RelatedDocumentDate;
}
