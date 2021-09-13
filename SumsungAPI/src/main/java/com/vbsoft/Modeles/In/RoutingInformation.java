package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.vbsoft.Modeles.In.Enums.CountryCode;
import com.vbsoft.Modeles.In.Enums.RoutingInformationItemCode;
import lombok.Data;

import javax.persistence.*;

/**
 * Информация о маршруте.
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 */
@Entity
@Table(name = "DeliveryRoutingInformation")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoutingInformation {

    /**
     * ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long ID;

    /**
     * Код типа маршрута (константа).
     */
    @JacksonXmlProperty(localName = "Type")
    private RoutingInformationItemCode type;

    /**
     * Наименование типа маршрута.
     */
    @JacksonXmlProperty(localName = "TypeName")
    private String typeName;

    /**
     * Возвращает Наименование типа маршрута.
     * Если постое возвращает значение из справочника.
     * @return Наименование типа маршрута.
     */
    public String getTypeName() {
        if (this.type != null && this.typeName == null)
            this.typeName = this.type.getValue();

        return this.typeName;
    }

    /**
     * Код страны.
     */
    @JacksonXmlProperty(localName = "CountryCode")
    private CountryCode countryCode;

    /**
     * Наименование страны.
     */
    @JacksonXmlProperty(localName = "CountryName")
    private String countryName;

    /**
     * Возвращает Код страны.
     * Если постое возвращает значение из справочника.
     * @return Код страны.
     */
    public String getCountryName() {
        if (this.countryCode != null && this.countryName == null)
            this.countryName = this.countryCode.getValue();

        return this.countryName;
    }

    /**
     * Код штата (обычно для США).
     */
    @JacksonXmlProperty(localName = "StateCode")
    private String stateCode;

    /**
     * Код города.
     */
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
