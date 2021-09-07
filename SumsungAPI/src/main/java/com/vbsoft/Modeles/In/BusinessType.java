package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.vbsoft.Modeles.In.Enums.BusinessTypeCode;
import com.vbsoft.Modeles.In.Enums.RelatedDocumentCode;
import com.vbsoft.Utils.Tools;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Arrays;

/**
 * Описание типа процесса.
 *
 * @author vd.zinovev
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "wwwSamsungBusinessType")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessType {

    /**
     * ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long ID;

    /**
     * Тип процесса.
     * @see BusinessType - справочник кодов процесса.
     */
    @JacksonXmlProperty(localName = "Code")
    private BusinessTypeCode code;

    /**
     * Описание типа процесса.
     */
    @JacksonXmlProperty(localName = "CodeDescription")
    private String codeDescription;

    /**
     * Получает значение описания по умолчанию.
     * @return Описание по умолчанию
     */
    public String getCodeDescription() {
        if (this.codeDescription == null & this.code != null)
            this.codeDescription = Tools.getEnumByAnnotation(BusinessTypeCode.class, this.code.getValue()).getValue();

        return this.codeDescription;
    }

    /**
     * Информация об отправке.
     */
    @JsonIgnore
    @OneToOne(mappedBy = "businessType")
    private PKFInfo info;
}
