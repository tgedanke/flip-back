package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

/**
 * Документ основание.
 *
 * @author vd.zinovev
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "wwwSamsungRelatedDocument")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RelatedDocument {

    /**
     * ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long ID;

    /**
     * Код типа документа.
     */
    @JacksonXmlProperty(localName = "Code")
    private RelatedDocumentCode code;

    /**
     * Описание типа документа основания.
     */
    @JacksonXmlProperty(localName = "CodeDescription")
    private String codeDescription;

    /**
     * Получает значение описания по умолчанию.
     * @return Описание по умолчанию
     */
    public String getCodeDescription() {
        if (this.codeDescription == null & this.code != null)
            this.codeDescription = Tools.getEnumByAnnotation(RelatedDocumentCode.class, this.code.getValue()).getValue();

        return this.codeDescription;
    }

    /**
     * Информация об отправке.
     */
    @JsonIgnore
    @OneToOne(mappedBy = "relatedDocument")
    private PKFInfo info;
}
