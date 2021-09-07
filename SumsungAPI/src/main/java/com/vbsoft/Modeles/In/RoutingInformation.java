package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "wwwSamsungRoutingInformation")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoutingInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long ID;

    @JacksonXmlProperty(localName = "Type")
    private String type;

    @JacksonXmlProperty(localName = "TypeName")
    private String typeName;

    @JacksonXmlProperty(localName = "CountryCode")
    private String countryCode;

    @JacksonXmlProperty(localName = "CountryName")
    private String countryName;

    @JacksonXmlProperty(localName = "StateCode")
    private String stateCode;

    @JacksonXmlProperty(localName = "CityOrPortCode")
    private String cityOrPortCode;

    @JacksonXmlProperty(localName = "CityOrPortName")
    private String cityOrPortName;

    @JacksonXmlProperty(localName = "RegionCode")
    private String regionCode;

    @JacksonXmlProperty(localName = "PostalCode")
    private String postalCode;


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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LocItem")
    private LocItem item;
}
