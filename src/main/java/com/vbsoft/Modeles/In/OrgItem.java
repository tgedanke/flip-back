package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * Элемент компании получателя.
 *
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 *
 */
@Entity
@Table(name = "DeliveryOrgItem")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrgItem {

    /**
     * ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long ID;

    /**
     * Номер по порядку.
     */
    @JacksonXmlProperty(localName = "SequenceNumber")
    private int sequenceNumber = 1;

    /**
     * Информация о компании.
     */
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "item", orphanRemoval = true, targetEntity = RelatedOrganization.class)
    @JacksonXmlProperty(localName = "RelatedOrganization")
    private RelatedOrganization organizations;

    public void setOrganizations(RelatedOrganization organizations) {
        if(organizations != null) {
            this.organizations = organizations;
            this.organizations.setItem(this);
        }
    }

    /**
     * Информация о доставке.
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "info")
    private PKFInfo info;

}
