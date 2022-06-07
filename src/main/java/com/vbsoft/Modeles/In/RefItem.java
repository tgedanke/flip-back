package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "DeliveryRefItem")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RefItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long ID;

    @JacksonXmlProperty(localName = "SequenceNumber")
    private int sequenceNumber = 1;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "item", orphanRemoval = true)
    @JoinColumn(name = "ReferenceDocumentNumber")
    @JacksonXmlProperty(localName = "ReferenceDocumentNumber")
    private ReferenceDocument referenceDocument;

    public void setReferenceDocument(ReferenceDocument referenceDocument) {
        if(referenceDocument != null) {
            this.referenceDocument = referenceDocument;
            this.referenceDocument.setItem(this);
        }
    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "item", orphanRemoval = true)
    @JoinColumn(name = "ReferenceOrderType")
    @JacksonXmlProperty(localName = "ReferenceOrderType")
    private ReferenceOrderType referenceOrderType;

    public void setReferenceOrderType(ReferenceOrderType referenceOrderType) {
        if(referenceOrderType != null) {
            this.referenceOrderType = referenceOrderType;
            this.referenceOrderType.setItem(this);
        }
    }

    @JacksonXmlProperty(localName = "Remark")
    private String remark;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "info")
    private PKFInfo info;
}
