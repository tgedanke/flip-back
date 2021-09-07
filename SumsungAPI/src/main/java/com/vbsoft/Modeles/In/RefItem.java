package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "wwwSamsungRefItem")
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

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "item", orphanRemoval = true)
    @JoinColumn(name = "ReferenceOrderType")
    @JacksonXmlProperty(localName = "ReferenceOrderType")
    private ReferenceOrderType referenceOrderType;

    @JacksonXmlProperty(localName = "Remark")
    private String remark;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "info")
    private PKFInfo info;
}
