package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "DeliveryPkgItem")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PkgItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long ID;

    @JacksonXmlProperty(localName = "SequenceNumber")
    private int sequenceNumber = 1;

    @JacksonXmlProperty(localName = "CartonNumber")
    private String cartonNumber;

    @JacksonXmlProperty(localName = "CartonType")
    private String cartonType;

    @JacksonXmlProperty(localName = "TrackingNumber")
    private String trackingNumber;

    @JacksonXmlProperty(localName = "ContainerNumber")
    private String containerNumber;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "item", orphanRemoval = true)
    @JoinColumn(name = "CargoInformation")
    @JacksonXmlProperty(localName = "CargoInformation")
    private CargoInformationPKG cargoInformation;

    public void setCargoInformation(CargoInformationPKG cargoInformation) {
        if(cargoInformation != null) {
            this.cargoInformation = cargoInformation;
            this.cargoInformation.setItem(this);
        }
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "info")
    private PKFInfo info;
}
