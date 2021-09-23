package com.vbsoft.Modeles.In.Enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Справочник 'Код статуса отправки сообщения'.
 * Справочник 1 в документе CELLO_EDI Code.
 *
 * @author vd.zinovev
 * @version 1.0
 * @since 1.0
 */
public enum MessageFunctionCode {

    @JsonProperty("9")
    Original("Original"),
    @JsonProperty("7")
    Resend("Resend"),
    @JsonProperty("3")
    Cancel("Cancel"),
    @JsonProperty("E")
    ErrorNAK("Error (NAK)"),
    @JsonProperty("S")
    Success("Success"),
    @JsonProperty("X")
    Xico("Xico"),
    @JsonProperty("P")
    PartnerXico("Partner Xico"),
    @JsonProperty("1")
    Error("Error"),
    @JsonProperty("5")
    ReverseClearNotice("Reverse Clear Notice"),
    @JsonProperty("6")
    Normal("Normal"),
    @JsonProperty("B")
    BLUploaded("B/L uploaded"),
    @JsonProperty("C")
    CancelSuccess("Cancel Success"),
    @JsonProperty("P")
    AgendaMentoBR("AgendaMento(BR)"),
    @JsonProperty("X01")
    EDInfoSend("ED info send"),
    @JsonProperty("X02")
    IncludingAttachment("Including attachment"),
    @JsonProperty("22")
    Last("Last"),
    @JsonProperty("11")
    Detail("detail"),
    @JsonProperty("18")
    FertilityChange("Fertility change"),
    @JsonProperty("35")
    EmptyContinerReturn("Empty Continer return"),
    @JsonProperty("32")
    Approve("Approve"),
    @JsonProperty("50")
    Reject("Reject");

    /**
     * Enum value.
     */
    @Getter
    private final String value;

    /**
     * Constructor.
     * @param VAL Enum value
     */
    MessageFunctionCode(String VAL) {
        this.value = VAL;
    }
}
