package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wwwSamsungPONumber")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PONumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long ID;

    @JacksonXmlProperty(localName = "ReferenceNumber")
    private String referenceNumber;

    @JacksonXmlProperty(localName = "ReferenceDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private Date referenceDate;

    @JacksonXmlProperty(localName = "POItemNumber")
    private String POItemNumber;

    @JsonIgnore
    @OneToOne(mappedBy = "PONumber")
    private MatItem item;
}
