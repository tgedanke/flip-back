package com.vbsoft.Modeles.Out.GENRES;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "GENRESInfoListDelivery")
@Data
public class GENRESInfoListDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long ID;

    @JacksonXmlProperty(localName = "SequenceNumber")
    private long sequenceNumber;

    @JacksonXmlProperty(localName = "HandlingInformation")
    private String information;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "generes")
    private GENRESDelivery genres;
}
