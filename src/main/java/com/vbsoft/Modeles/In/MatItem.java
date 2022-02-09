package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "DeliveryMatItem")
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

    public void setMaterial(Material material) {
        this.material = material;
        this.material.setItem(this);
    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "item", orphanRemoval = true)
    @JoinColumn(name = "Plant")
    @JacksonXmlProperty(localName = "Plant")
    private Plant plant;

    public void setPlant(Plant plant) {
        this.plant = plant;
        this.plant.setItem(this);
    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "item", orphanRemoval = true)
    @JoinColumn(name = "Plant")
    @JacksonXmlProperty(localName = "StorageLocation")
    private StorageLocation storageLocation;

    public void setStorageLocation(StorageLocation storageLocation) {
        this.storageLocation = storageLocation;
        this.storageLocation.setItem(this);
    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "item", orphanRemoval = true)
    @JoinColumn(name = "CargoInformation")
    @JacksonXmlProperty(localName = "CargoInformation")
    private CargoInformation cargoInformation;

    public void setCargoInformation(CargoInformation cargoInformation) {
        this.cargoInformation = cargoInformation;
        this.cargoInformation.setItem(this);
    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "item", orphanRemoval = true)
    @JoinColumn(name = "PONumber")
    @JacksonXmlProperty(localName = "PONumber")
    private PONumber PONumber;

    public void setPONumber(com.vbsoft.Modeles.In.PONumber PONumber) {
        this.PONumber = PONumber;
        this.PONumber.setItem(this);
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "info")
    private PKFInfo info;
}
