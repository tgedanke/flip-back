package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * SAP Material Division.
 *
 * @author vd.zinovev
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "wwwSamsungDivision")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Division extends CodeDescription{

    /**
     * Информация об отправке.
     */
    @JsonIgnore
    @OneToOne(mappedBy = "division")
    private PKFInfo info;

}
