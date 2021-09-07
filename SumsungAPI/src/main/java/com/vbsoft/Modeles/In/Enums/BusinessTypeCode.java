package com.vbsoft.Modeles.In.Enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Тип процесса в соответствии.
 * Справочник 27 в документе CELLO_EDI Code.
 *
 * @author vd.zinovev
 * @version 1.0
 * @since 1.0
 */
public enum BusinessTypeCode {

    @JsonProperty("EX")
    Export("Export"),
    @JsonProperty("IM")
    Import("Import"),
    @JsonProperty("LD")
    LocalDelivery("Local Delivery"),
    @JsonProperty("WM")
    Warehouse("Warehouse"),
    @JsonProperty("LP")
    LocalPurchasing("Local Purchasing");

    /**
     * Enum value.
     */
    @Getter
    private final String value;

    /**
     * Enum constructor.
     * @param VAL Current enum.
     */
    BusinessTypeCode(final String VAL) {
        this.value = VAL;
    }
}
