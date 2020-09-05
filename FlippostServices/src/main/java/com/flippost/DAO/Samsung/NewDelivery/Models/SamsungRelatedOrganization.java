package com.flippost.DAO.Samsung.NewDelivery.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SamsungRelatedOrganization {

    private int id;

    @JacksonXmlProperty(localName = "Type")
    private String Type;

    @JacksonXmlProperty(localName = "TypeName")
    private String TypeName;

    @JacksonXmlProperty(localName = "Name")
    private String Name;

    @JacksonXmlProperty(localName = "Address1")
    private String Address1;

    @JacksonXmlProperty(localName = "Address2")
    private String Address2;

    @JacksonXmlProperty(localName = "Address3")
    private String Address3;

    @JacksonXmlProperty(localName = "Address4")
    private String Address4;

    @JacksonXmlProperty(localName = "Address5")
    private String Address5;

    @JacksonXmlProperty(localName = "TelephoneNumber")
    private String TelephoneNumber;

    @JacksonXmlProperty(localName = "FaxNumber")
    private String FaxNumber;

    @JacksonXmlProperty(localName = "Code")
    private String Code;

    @JacksonXmlProperty(localName = "ContactPerson")
    private String ContactPerson;
}
