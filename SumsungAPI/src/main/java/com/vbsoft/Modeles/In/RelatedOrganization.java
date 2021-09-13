package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import javax.persistence.*;

/**
 *
 */
@Entity
@Table(name = "DeliveryRelatedOrganization")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RelatedOrganization {

    /**
     * ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long ID;

    /**
     * Тип компании (организационноправовая форма).
     */
    @JacksonXmlProperty(localName = "Type")
    private String type;

    /**
     * Тип компании (константа).
     * eg. SH = Shipper,
     * CN=Consignee,
     * CA=Carrier,
     * Forwarder, etc.
     */
    @JacksonXmlProperty(localName = "TypeName")
    private String typeName;

    /**
     * Наименование компании.
     */
    @JacksonXmlProperty(localName = "Name")
    private String name;

    /**
     * Адрес.
     */
    @JacksonXmlProperty(localName = "Address1")
    private String address1;

    /**
     * Индекс.
     */
    @JacksonXmlProperty(localName = "Address2")
    private String address2;

    /**
     * Город.
     */
    @JacksonXmlProperty(localName = "Address3")
    private String address3;

    /**
     * Доп. поле 'Адрес'.
     */
    @JacksonXmlProperty(localName = "Address4")
    private String address4;

    /**
     * Доп. поле 'Адрес'.
     */
    @JacksonXmlProperty(localName = "Address5")
    private String address5;

    /**
     * Номер телефона.
     */
    @JacksonXmlProperty(localName = "TelephoneNumber")
    private String telephoneNumber;

    /**
     * Номер факса.
     */
    @JacksonXmlProperty(localName = "FaxNumber")
    private String faxNumber;

    /**
     * Код компании.
     */
    @JacksonXmlProperty(localName = "Code")
    private String code;

    /**
     * Контактное лицо.
     */
    @JacksonXmlProperty(localName = "ContactPerson")
    private String contactPerson;

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
     * Элемент компании получателя.
     */
    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OrgItem")
    private OrgItem item;
}
