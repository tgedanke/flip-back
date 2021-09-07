package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.vbsoft.Modeles.Out.GENRES.GENRESDelivery;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "wwwSamsungRelatedOrganization")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RelatedOrganization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long ID;

    @JacksonXmlProperty(localName = "Type")
    private String type;

    @JacksonXmlProperty(localName = "TypeName")
    private String typeName;

    @JacksonXmlProperty(localName = "Name")
    private String name;

    @JacksonXmlProperty(localName = "Address1")
    private String address1;

    @JacksonXmlProperty(localName = "Address2")
    private String address2;

    @JacksonXmlProperty(localName = "Address3")
    private String address3;

    @JacksonXmlProperty(localName = "Address4")
    private String address4;

    @JacksonXmlProperty(localName = "Address5")
    private String address5;

    @JacksonXmlProperty(localName = "TelephoneNumber")
    private String telephoneNumber;

    @JacksonXmlProperty(localName = "FaxNumber")
    private String faxNumber;

    @JacksonXmlProperty(localName = "Code")
    private String code;

    @JacksonXmlProperty(localName = "ContactPerson")
    private String contactPerson;

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
    @JoinColumn(name = "OrgItem")
    private OrgItem item;
}
