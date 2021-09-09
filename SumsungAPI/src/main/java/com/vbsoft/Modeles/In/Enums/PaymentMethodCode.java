package com.vbsoft.Modeles.In.Enums;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Справочник 'Форма оплаты'.
 * Справочник 29 в документе CELLO_EDI Code.
 *
 * @author vd.zinovev
 * @version 1.0
 * @since 1.0
 */
public enum PaymentMethodCode {
    @JsonProperty("10")
    Cash,
    @JsonProperty("20")
	Cheque,
    @JsonProperty("30")
	CreditCard,
    @JsonProperty("40")
	DirectDeposit
}
