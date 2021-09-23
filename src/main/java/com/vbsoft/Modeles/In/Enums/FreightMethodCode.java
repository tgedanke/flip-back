package com.vbsoft.Modeles.In.Enums;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Справочник 'Тип оплаты'.
 * Справочник 7 в документе CELLO_EDI Code.
 *
 * @author vd.zinovev
 * @version 1.0
 * @since 1.0
 */
public enum FreightMethodCode {

    @JsonProperty("30")
    PrepaidOther,
    @JsonProperty("31")
	Prepaid,
    @JsonProperty("32")
	Collect,
    @JsonProperty("33")
	CollectOther
}
