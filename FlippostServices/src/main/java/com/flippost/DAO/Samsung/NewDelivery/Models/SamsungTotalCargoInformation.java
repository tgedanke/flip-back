package com.flippost.DAO.Samsung.NewDelivery.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SamsungTotalCargoInformation {

    private int id;

    @JacksonXmlProperty(localName = "PackingQuantity")
    private String PackingQuantity;

    @JacksonXmlProperty(localName = "PackingQuantityCode")
    private String PackingQuantityCode;

    @JacksonXmlProperty(localName = "VesselFlightTruckName1")
    private String VesselFlightTruckName1;

    @JacksonXmlProperty(localName = "CartonPackingQuantity")
    private String CartonPackingQuantity;

    @JacksonXmlProperty(localName = "CartonPackingQuantityCode")
    private String CartonPackingQuantityCode;

    @JacksonXmlProperty(localName = "Quantity")
    private int Quantity;

    @JacksonXmlProperty(localName = "QuantityCode")
    private String QuantityCode;

    @JacksonXmlProperty(localName = "Volume")
    private double Volume;

    @JacksonXmlProperty(localName = "VolumeCode")
    private String VolumeCode;

    @JacksonXmlProperty(localName = "GrossWeight")
    private double GrossWeight;

    @JacksonXmlProperty(localName = "GrossWeightCode")
    private String GrossWeightCode;

    @JacksonXmlProperty(localName = "NetWeight")
    private double NetWeight;

    @JacksonXmlProperty(localName = "NetWeightCode")
    private String NetWeightCode;

    @JacksonXmlProperty(localName = "ChargeableWeight")
    private double ChargeableWeight;

    @JacksonXmlProperty(localName = "ChargeableWeightCode")
    private String ChargeableWeightCode;
}
