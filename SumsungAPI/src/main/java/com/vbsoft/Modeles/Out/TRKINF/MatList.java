package com.vbsoft.Modeles.Out.TRKINF;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "EvnList")
@Data
public class MatList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long ID;

    @JacksonXmlProperty(localName = "SequenceNumber")
    private long sequenceNumber;

    @JacksonXmlProperty(localName = "Material")
    private String material;

    @JacksonXmlProperty(localName = "GoodsDescription")
    private String goodsDescription;

    @JacksonXmlProperty(localName = "ItemNumber")
    private long itemNumber;

    @JacksonXmlProperty(localName = "Quantity")
    private int quantity;

    @JacksonXmlProperty(localName = "QuantityCode")
    private String quantityCode;

    @JacksonXmlProperty(localName = "Volume")
    private float volume;

    @JacksonXmlProperty(localName = "VolumeCode")
    private String volumeCode = "M3";

    @JacksonXmlProperty(localName = "GrossWeight")
    private float GrossWeight;

    @JacksonXmlProperty(localName = "GrossWeightCode")
    private String grossWeightCode = "KG";

    @JacksonXmlProperty(localName = "ChargeableWeight")
    private float chargeableWeight;

    @JacksonXmlProperty(localName = "ChargeableWeightCode")
    private String chargeableWeightCode = "KG";

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "infoID")
    private TRKINF info;
}
