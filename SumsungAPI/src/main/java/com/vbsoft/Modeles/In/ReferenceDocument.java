package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "wwwSamsungReferenceDocument")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReferenceDocument extends CodeDescription{

    @JsonIgnore
    @OneToOne(mappedBy = "referenceDocument")
    private RefItem item;

}
