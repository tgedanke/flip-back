package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * Элемент информации о маршруте.
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 */
@Entity
@Table(name = "DeliveryLocItem")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocItem {

    /**
     * ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long ID;

    /**
     * Порядковый номер.
     */
    @JacksonXmlProperty(localName = "SequenceNumber")
    private int sequenceNumber = 1;

    /**
     * Информация о маршруте.
     */
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "item", orphanRemoval = true)
    @JacksonXmlProperty(localName = "RoutingInformation")
    private RoutingInformation routingInformation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "info")
    private PKFInfo info;

}
