package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DeliveryPlant")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Plant  extends CodeDescription {

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "matItemID")
    private MatItem item;
}
