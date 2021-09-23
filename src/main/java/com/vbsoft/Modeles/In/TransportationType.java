package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.vbsoft.Modeles.In.Enums.TransportationTypeCode;
import lombok.Data;

import javax.persistence.*;

/**
 * Справочник кодов 'Тип доставки'.
 *
 * @author vd.zinovev
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "DeliveryTransportationType")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransportationType {

    /**
     * ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long ID;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "infoID")
    private PKFInfo info;

    /**
     * Код типа доставки.
     */
    @JacksonXmlProperty(localName = "Code")
    private TransportationTypeCode code;

    /**
     * Описание типа доставки.
     */
    @JacksonXmlProperty(localName = "CodeDescription")
    private String codeDescription;

    /**
     * Получает значение описания по умолчанию.
     * @return Описание по умолчанию
     */
    public String getCodeDescription() {
        if (this.codeDescription == null & this.code != null)
            this.codeDescription = this.code.getValue();


        return this.codeDescription;
    }
}
