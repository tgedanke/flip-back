package com.flippost.DAO.Samsung.NewDelivery.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SamsungOrgItem {

    private int id;

    private SamsungNewDelivery delivery;

    @JacksonXmlProperty(localName = "SequenceNumber")
    private int SequenceNumber;

    @JacksonXmlProperty(localName = "Field1")
    private String Field1;

    @JacksonXmlProperty(localName = "Field2")
    private String Field2;

    @JacksonXmlProperty(localName = "Field3")
    private String Field3;

    @JacksonXmlProperty(localName = "Field4")
    private String Field4;

    @JacksonXmlProperty(localName = "Field5")
    private String Field5;
}
