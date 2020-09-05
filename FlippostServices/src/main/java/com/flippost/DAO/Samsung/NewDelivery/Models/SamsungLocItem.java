package com.flippost.DAO.Samsung.NewDelivery.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SamsungLocItem {

    private int id;

    @JacksonXmlProperty(localName = "SequenceNumber")
    private int SequenceNumber;

    @JacksonXmlProperty(localName = "RoutingInformation")
    private SamsungRoutingInformation information;
}
