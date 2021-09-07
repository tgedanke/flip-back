package com.vbsoft.Modeles.In;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Супер класс для частей типа 'Код', 'Описание'.
 */
@MappedSuperclass
public class CodeDescription {

    /**
     * ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long ID;

    /**
     * Структурный код.
     */
    @JacksonXmlProperty(localName = "Code")
    private String code;

    /**
     * Описание структурного кода.
     */
    @JacksonXmlProperty(localName = "CodeDescription")
    private String codeDescription;


}
