package com.vbsoft.Modeles.Out.TRKINF.Enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Пееречисление статусов трека.
 */
public enum TrackingStatuses {

    @JsonProperty("01")
    Warehouse("Warehouse"),

    @JsonProperty("02")
    MainWarehouse("3PL Main warehouse"),

    @JsonProperty("03")
    RegionalWarehouse("3PL regional warehouse"),

    @JsonProperty("04")
    Airport("Airport"),

    @JsonProperty("05")
    Customer("Customer");

    @Getter
    private final String VAL;

    TrackingStatuses(String val) {
        this.VAL = val;
    }
}
