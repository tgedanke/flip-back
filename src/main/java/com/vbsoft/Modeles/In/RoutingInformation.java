package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.vbsoft.Modeles.In.Enums.CountryCode;
import com.vbsoft.Modeles.In.Enums.RoutingInformationCode;
import lombok.Data;

import javax.persistence.*;

/**
 * Информация о маршруте.
 *
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 *
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
     * Код типа маршрута.
     */
    @JacksonXmlProperty(localName = "Type")
    private RoutingInformationCode type;

    /**
     * Наименование типа маршрута.
     */
    @JacksonXmlProperty(localName = "TypeName")
    private String typeName;

    /**
     * Возвращает наименование маршрута из справочника.
     * @return Наименование маршрута
     */
    public String getTypeName() {
        if(this.type != null && this.typeName == null)
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
     * Возвращает наименование страны из справочника.
     * @return Наименование страны.
     */
    public String getCountryName() {
        if(this.countryCode != null && this.countryName == null)
            this.countryName = this.countryCode.getValue();
        return this.countryName;
    }

    @JacksonXmlProperty(localName = "StateCode")
    private String stateCode;

    @JacksonXmlProperty(localName = "CityOrPortCode")
    private String cityOrPortCode;

    /**
     * Наименование города.
     */
    @JacksonXmlProperty(localName = "CityOrPortName")
    private String cityOrPortName;

    /**
     * Код региона.
     */
    @JacksonXmlProperty(localName = "RegionCode")
    private String regionCode;

    /**
     * Почтовый индекс.
     */
    @JacksonXmlProperty(localName = "PostalCode")
    private String postalCode;

    /**
     * Дополнительное поле 1.
     */
    @JacksonXmlProperty(localName = "Field1")
    private String field1;

    /**
     * Дополнительное поле 2.
     */
    @JacksonXmlProperty(localName = "Field2")
    private String field2;

    /**
     * Дополнительное поле 3.
     */
    @JacksonXmlProperty(localName = "Field3")
    private String field3;

    /**
     * Дополнительное поле 4.
     */
    @JacksonXmlProperty(localName = "Field4")
    private String field4;

    /**
     * Дополнительное поле 5.
     */
    @JacksonXmlProperty(localName = "Field5")
    private String field5;

    /**
     * Информация о списке.
     */
    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LocItem")
    private LocItem item;
}
