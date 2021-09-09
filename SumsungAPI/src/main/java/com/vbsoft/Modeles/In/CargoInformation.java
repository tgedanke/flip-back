package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.vbsoft.Modeles.In.Enums.PackingQuantityCode;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "DeliveryCargoInformation")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CargoInformation {

    /**
     * ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long ID;

    /**
     * Общее количество упаковок в отправке.
     */
    @JacksonXmlProperty(localName = "PackingQuantity")
    private Integer packingQuantity;

    /**
     * Код количества.
     */
    @JacksonXmlProperty(localName = "PackingQuantityCode")
    private PackingQuantityCode packingQuantityCode;

    /**
     * Общее количество коробок в отправке.
     */
    @JacksonXmlProperty(localName = "CartonPackingQuantity")
    private Integer cartonPackingQuantity;

    /**
     * Код количества коробок.
     */
    @JacksonXmlProperty(localName = "CartonPackingQuantityCode")
    private PackingQuantityCode cartonPackingQuantityCode;

    /**
     * Общее количество элементов в отправке.
     */
    @JacksonXmlProperty(localName = "Quantity")
    private Integer quantity;

    /**
     * Код единиц измерения количества.
     */
    @JacksonXmlProperty(localName = "QuantityCode")
    private PackingQuantityCode quantityCode;

    /**
     * Общий объем доставки.
     */
    @JacksonXmlProperty(localName = "Volume")
    private Float volume;

    /**
     * Код единиц изменения объема.
     */
    @JacksonXmlProperty(localName = "VolumeCode")
    private String volumeCode;

    /**
     * Общий вес (gross) доставки.
     */
    @JacksonXmlProperty(localName = "GrossWeight")
    private Float grossWeight;

    /**
     * Код единиц изменения веса.
     */
    @JacksonXmlProperty(localName = "GrossWeightCode")
    private PackingQuantityCode grossWeightCode;

    /**
     * Общий вес (net) доставки.
     */
    @JacksonXmlProperty(localName = "NetWeight")
    private Float netWeight;

    /**
     * Код единиц изменения веса.
     */
    @JacksonXmlProperty(localName = "NetWeightCode")
    private PackingQuantityCode netWeightCode;

    /**
     * Оплачиваемый вес доставки.
     */
    @JacksonXmlProperty(localName = "ChargeableWeight")
    private Float chargeableWeight;

    /**
     * Код единиц изменения веса.
     */
    @JacksonXmlProperty(localName = "ChargeableWeightCode")
    private PackingQuantityCode chargeableWeightCode;
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
    @OneToOne
    @JoinColumn(name = "infoID")
    private PKFInfo info;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "MatItemID")
    private MatItem item;
}
