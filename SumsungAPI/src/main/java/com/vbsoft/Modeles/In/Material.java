package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "wwwSamsungMaterial")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long ID;

    @JsonIgnore
    @OneToOne(mappedBy = "material")
    private PKFInfo info;

    @JacksonXmlProperty(localName = "Material")
    private String material;

    @JacksonXmlProperty(localName = "GoodsDescription1")
    private String goodsDescription1;

    @JacksonXmlProperty(localName = "GoodsDescription2")
    private String goodsDescription2;

    @JacksonXmlProperty(localName = "GoodsDescription3")
    private String goodsDescription3;

    @JacksonXmlProperty(localName = "GoodsDescription4")
    private String goodsDescription4;

    @JacksonXmlProperty(localName = "GoodsDescription5")
    private String goodsDescription5;

    @JacksonXmlProperty(localName = "ItemNumber")
    private int itemNumber;

    @JacksonXmlProperty(localName = "BuyerMaterial")
    private String buyerMaterial;

    @OneToOne(mappedBy = "material")
    private MatItem item;
}
