package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "wwwSamsungMatItem")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long ID;

    @JacksonXmlProperty(localName = "SequenceNumber")
    private int sequenceNumber = 1;

    @JacksonXmlProperty(localName = "BatchID")
    private String batchID;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "item", orphanRemoval = true)
    @JoinColumn(name = "Material")
    @JacksonXmlProperty(localName = "Material")
    private Material material;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "item", orphanRemoval = true)
    @JoinColumn(name = "Plant")
    @JacksonXmlProperty(localName = "Plant")
    private Plant plant;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "item", orphanRemoval = true)
    @JoinColumn(name = "Plant")
    @JacksonXmlProperty(localName = "StorageLocation")
    private StorageLocation storageLocation;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "item", orphanRemoval = true)
    @JoinColumn(name = "CargoInformation")
    @JacksonXmlProperty(localName = "CargoInformation")
    private CargoInformation cargoInformation;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "item", orphanRemoval = true)
    @JoinColumn(name = "PONumber")
    @JacksonXmlProperty(localName = "PONumber")
    private PONumber PONumber;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "info")
    private PKFInfo info;
}
