package com.flippost.DAO.Samsung.NewDelivery.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SamsungDivision {

    private int id;

    @JacksonXmlProperty(localName = "Code")
    private String Code;

    @JacksonXmlProperty(localName = "CodeDescription")
    private String CodeDescription;
}
