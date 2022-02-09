package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.vbsoft.Modeles.In.Enums.RelatedOrganizationCode;
import lombok.Data;

import javax.persistence.*;

/**
 * Информация о компании.
 *
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
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
    private RelatedOrganizationCode type;

    /**
     * Тип компании.
     */
    @JacksonXmlProperty(localName = "TypeName")
    private String typeName;

    /**
     * Получение типа компании по справочнику.
     * @return Тип компании
     */
    public String getTypeName() {
        if (this.typeName == null && this.type != null)
            this.typeName = this.type.getValue();
        return this.typeName;
    }

    /**
     * Наименование компании.
     */
    @JacksonXmlProperty(localName = "Name")
    private String name;

    /**
     * Адрес 1.
     */
    @JacksonXmlProperty(localName = "Address1")
    private String address1;

    /**
     * Адрес 2.
     */
    @JacksonXmlProperty(localName = "Address2")
    private String address2;

    /**
     * Адрес 3.
     */
    @JacksonXmlProperty(localName = "Address3")
    private String address3;

    /**
     * Адрес 4.
     */
    @JacksonXmlProperty(localName = "Address4")
    private String address4;

    /**
     * Адрес 5.
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
     * Код компании (SAP самсунг).
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
     * Ключ списка.
     */
    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OrgItem", nullable = false)
    private OrgItem item;
}
