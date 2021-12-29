package com.vbsoft.Modeles.Out.TRKINF.Enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Событие трека.
 */
public enum TrackEvent {

    @JsonProperty("20")
    Departure("Departure"),
    @JsonProperty("10")
    Arrival("Arrival");

    @Getter
    private final String VAL;

    TrackEvent(String val) {
        this.VAL = val;
    }

}
