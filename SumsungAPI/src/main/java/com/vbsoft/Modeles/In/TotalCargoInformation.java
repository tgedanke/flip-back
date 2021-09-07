package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "wwwSamsungTotalCargoInformation")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TotalCargoInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long ID;

    @JacksonXmlProperty(localName = "PackingQuantity")
    private int packingQuantity;

    @JacksonXmlProperty(localName = "PackingQuantityCode")
    private String packingQuantityCode;

    @JacksonXmlProperty(localName = "CartonPackingQuantity")
    private int cartonPackingQuantity;

    @JacksonXmlProperty(localName = "CartonPackingQuantityCode")
    private String cartonPackingQuantityCode;

    @JacksonXmlProperty(localName = "Quantity")
    private int quantity;

    @JacksonXmlProperty(localName = "QuantityCode")
    private String quantityCode;

    @JacksonXmlProperty(localName = "Volume")
    private float volume;

    @JacksonXmlProperty(localName = "VolumeCode")
    private String volumeCode;

    @JacksonXmlProperty(localName = "GrossWeight")
    private float grossWeight;

    @JacksonXmlProperty(localName = "GrossWeightCode")
    private String grossWeightCode;

    @JacksonXmlProperty(localName = "NetWeight")
    private float netWeight;

    @JacksonXmlProperty(localName = "NetWeightCode")
    private String netWeightCode;

    @JacksonXmlProperty(localName = "ChargeableWeight")
    private float chargeableWeight;

    @JacksonXmlProperty(localName = "ChargeableWeightCode")
    private String chargeableWeightCode;

    @JacksonXmlProperty(localName = "Field1")
    private String field1;

    @JacksonXmlProperty(localName = "Field2")
    private String field2;

    @JacksonXmlProperty(localName = "Field3")
    private String field3;

    @JacksonXmlProperty(localName = "Field4")
    private String field4;

    @JacksonXmlProperty(localName = "Field5")
    private String field5;

    @JsonIgnore
    @OneToOne(mappedBy = "division")
    private PKFInfo info;
}
