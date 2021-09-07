package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "wwwSamsungStorageLocation")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StorageLocation extends CodeDescription {

    @JsonIgnore
    @OneToOne(mappedBy = "storageLocation")
    private MatItem item;

    @JacksonXmlProperty(localName = "Grade")
    private String grade;
}
