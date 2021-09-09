package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DeliveryStorageLocation")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StorageLocation extends CodeDescription {

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "MatItemID")
    private MatItem item;

    @JacksonXmlProperty(localName = "Grade")
    private String grade;
}
