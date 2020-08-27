package com.flippost.DAO.Samsung.NewDelivery.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SamsungTransportationType {

    private int id;

    @JacksonXmlProperty(localName = "Code")
    private String Code;

    @JacksonXmlProperty(localName = "CodeDescription")
    private String CodeDescription;

    @JacksonXmlProperty(localName = "VesselFlightTruckName1")
    private String VesselFlightTruckName1;

    @JacksonXmlProperty(localName = "VesselFlightTruckName2")
    private String VesselFlightTruckName2;

    @JacksonXmlProperty(localName = "IncotermsCode")
    private String IncotermsCode;

    @JacksonXmlProperty(localName = "IncotermsPlace")
    private String IncotermsPlace;

    @JacksonXmlProperty(localName = "FreightMethod")
    private String FreightMethod;

    @JacksonXmlProperty(localName = "PaymentMethod1")
    private String PaymentMethod1;

    @JacksonXmlProperty(localName = "PaymentMethod2")
    private String PaymentMethod2;

    @JacksonXmlProperty(localName = "PaymentMethod3")
    private String PaymentMethod3;

    @JacksonXmlProperty(localName = "PaymentMethod4")
    private String PaymentMethod4;

    @JacksonXmlProperty(localName = "PaymentMethod5")
    private String PaymentMethod5;

    @JacksonXmlProperty(localName = "PaymentPeriod1")
    private String PaymentPeriod1;

    @JacksonXmlProperty(localName = "PaymentPeriod2")
    private String PaymentPeriod2;

    @JacksonXmlProperty(localName = "Commission")
    private String Commission;

    @JacksonXmlProperty(localName = "LCIssueBank")
    private String LCIssueBank;

    @JacksonXmlProperty(localName = "ShippingDate")
    private String ShippingDate;

    @JacksonXmlProperty(localName = "ShippingTime")
    private String ShippingTime;

    @JacksonXmlProperty(localName = "EstimateDateofArrival")
    private String EstimateDateofArrival;

}
