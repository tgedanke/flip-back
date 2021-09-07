package com.vbsoft.Modeles.Out.TRKINF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EvnList")
@Data
public class EvnList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long ID;

    @JacksonXmlProperty(localName = "SequenceNumber")
    private long sequenceNumber;

    @JacksonXmlProperty(localName = "EventType")
    private String eventType;

    @JacksonXmlProperty(localName = "EventName")
    private String eventName;

    @JacksonXmlProperty(localName = "CountryCode")
    private String countryCode;

    @JacksonXmlProperty(localName = "CountryName")
    private String countryName;

    @JacksonXmlProperty(localName = "CityCode")
    private String cityCode;

    @JacksonXmlProperty(localName = "CityName")
    private String cityName;

    @JacksonXmlProperty(localName = "EstimateDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private Date estimateDate;

    @JacksonXmlProperty(localName = "EstimateTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HHmmss")
    private Date estimateTime;

    @JacksonXmlProperty(localName = "ActualDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private Date actualDate;

    @JacksonXmlProperty(localName = "ActualTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HHmmss")
    private Date actualTime;

    @JacksonXmlProperty(localName = "CurrentEvent")
    private String currentEvent;

    @JacksonXmlProperty(localName = "Description")
    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "infoID")
    private TRKINF info;
}
