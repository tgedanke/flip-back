package com.vbsoft.Modeles.In.Enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Справочник 'Код типа маршрута'.
 * Справочник 3 в документе CELLO_EDI Code.
 *
 * @author vd.zinovev
 * @version 1.0
 * @since 1.0
 */
public enum RoutingInformationCode {

    @JsonProperty("88")
    RECEIPT_PLACE("RECEIPT_PLACE"),
    @JsonProperty("9")
    LOADING_PORT("LOADING_PORT"),
    @JsonProperty("12")
    DISCHARGE_PORT("DISCHARGE_PORT"),
    @JsonProperty("8")
    DELIVERY_PLACE("DELIVERY_PLACE"),
    @JsonProperty("6TA")
    PICKUP_PLACE("PICKUP_PLACE"),
    @JsonProperty("49")
    TRANSIT_PORT("TRANSIT_PORT"),
    @JsonProperty("99")
    RETURN_PORT("RETURN_PORT"),
    @JsonProperty("27")
    CountryOfOrigin("Country of Origin"),
    @JsonProperty("20")
    FinalDestination("Final Destination");

    @Getter
    private final String value;

    RoutingInformationCode(final String VAL) {
        this.value = VAL;
    }
}
