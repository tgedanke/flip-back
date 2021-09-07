package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "wwwSamsungCargoInformation")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CargoInformation {

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

    @JacksonXmlProperty(localName = "ManufacturerCountry")
    private String manufacturerCountry;

    @JacksonXmlProperty(localName = "ManufacturerCompany")
    private String manufacturerCompany;

    @JacksonXmlProperty(localName = "ContainerNumber")
    private String containerNumber;

    @JacksonXmlProperty(localName = "SealNumber")
    private String sealNumber;

    @JacksonXmlProperty(localName = "ContainerType")
    private String containerType;

    @JacksonXmlProperty(localName = "ContainerTypeName")
    private String containerTypeName;

    @JacksonXmlProperty(localName = "CustomerPONumber")
    private String customerPONumber;

    @JsonIgnore
    @OneToOne(mappedBy = "cargoInformation")
    private PKFInfo info;
}
