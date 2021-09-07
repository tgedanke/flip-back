package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "wwwSamsungReferenceOrderType")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReferenceOrderType extends CodeDescription{

    @JsonIgnore
    @OneToOne(mappedBy = "referenceOrderType")
    private RefItem item;
}
