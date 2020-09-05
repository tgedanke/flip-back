package com.flippost.DAO.Samsung.NewDelivery.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SamsungRoutingInformation {

    private int id;

    @JacksonXmlProperty(localName = "Type")
    private String Type;

    @JacksonXmlProperty(localName = "TypeName")
    private String TypeName;

    @JacksonXmlProperty(localName = "CountryCode")
    private String CountryCode;

    @JacksonXmlProperty(localName = "CountryName")
    private String CountryName;

    @JacksonXmlProperty(localName = "CityOrPortName")
    private String CityOrPortName;

    @JacksonXmlProperty(localName = "RegionCode")
    private String RegionCode;

    @JacksonXmlProperty(localName = "PostalCode")
    private String PostalCode;
}
