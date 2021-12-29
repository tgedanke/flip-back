package com.vbsoft.Modeles.Out.TRKINF;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.vbsoft.Modeles.In.Enums.PackingQuantityCode;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TRKINFEvnList")
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
    private PackingQuantityCode quantityCode;

    @JacksonXmlProperty(localName = "Volume")
    private float volume;

    @JacksonXmlProperty(localName = "VolumeCode")
    private String volumeCode = "M3";

    @JacksonXmlProperty(localName = "GrossWeight")
    private float GrossWeight;

    @JacksonXmlProperty(localName = "GrossWeightCode")
    private PackingQuantityCode grossWeightCode = PackingQuantityCode.Kilogram;

    @JacksonXmlProperty(localName = "ChargeableWeight")
    private float chargeableWeight;

    @JacksonXmlProperty(localName = "ChargeableWeightCode")
    private PackingQuantityCode chargeableWeightCode = PackingQuantityCode.Kilogram;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "infoID")
    private TRKINF info;
}
