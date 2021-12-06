package com.vbsoft.Modeles.In.Enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Справочник 'Код типа доставки'
 * Справочник 6 в документе CELLO_EDI Code.
 *
 * @version 1.0
 * @since 1.0
 * @author vd.zinovev
 */
public enum TransportationTypeCode {

    @JsonProperty("01")
    Truck ("Truck"),

    @JsonProperty("02")
	Mail ("Mail"),

    @JsonProperty("03")
	Rail ("Rail"),

    @JsonProperty("04")
	Sea ("Sea"),

    @JsonProperty("05")
	Air ("Air"),

    @JsonProperty("C1")
    FCLRail ("FCL-Rail"),

    @JsonProperty("C2")
    LCLRail ("LCL-Rail"),

    @JsonProperty("C3")
    RailAir ("Rail-Air"),

    @JsonProperty("C4")
    FTLAir ("FTL-Air"),

    @JsonProperty("C5")
    LTLAir ("LTL-Air"),

    @JsonProperty("C6")
    FTLBarge ("FTL-Barge"),

    @JsonProperty("C7")
    LTLBarge ("LTL-Barge"),

    @JsonProperty("C8")
    SeaAir ("Sea-Air"),

    @JsonProperty("C9")
    SeaTrain ("Sea-Train"),

    @JsonProperty("F1")
    TrailerOnFlatcar ("Trailer On Flatcar"),

    @JsonProperty("F2")
    ContainerOnFlatcar ("Container On Flatcar"),

    @JsonProperty("M1")
    MailParcel ("Mail ( Parcel )"),

    @JsonProperty("M2")
    Express ("Express"),

    @JsonProperty("M3")
    HandCarry ("Hand Carry"),

    @JsonProperty("NT")
    NonTransport ("non-transport"),

    @JsonProperty("P1")
    Plane ("Plane"),

    @JsonProperty("P2")
    Charter ("Charter"),

    @JsonProperty("R1")
    Train ("Train"),

    @JsonProperty("R2")
    ExpressTrain ("Express Train"),

    @JsonProperty("R3")
    BlackTrain ("Black Train"),

    @JsonProperty("S1")
    ShipFCL ("Ship(FCL)"),

    @JsonProperty("S2")
    ShipLCL ("Ship(LCL)"),

    @JsonProperty("S3")
    Barge ("Barge"),

    @JsonProperty("S4")
    Ferry ("Ferry"),

    @JsonProperty("T1")
    FullTruckFTL ("Full Truck(FTL)"),

    @JsonProperty("T2")
    ThanTruckLTL ("Le. Than Truck(LTL)"),

    @JsonProperty("T3")
    FullContainerFCL ("Full Container(FCL)"),

    @JsonProperty("T4")
    TnContLCL ("Le. Th. Cont.(LCL)"),

    @JsonProperty("T5")
    TeamDrivers ("Team-Drivers"),

    @JsonProperty("WM")
    WareHouseFREINVReceivingOnly ("WM	WareHouse ( FREINV receiving Only )");

    /**
     * Значение enum.
     */
    @Getter
    private final String value;

    /**
     * Конструктор.
     * @param VAL Текущее значение
     */
    TransportationTypeCode(String VAL) {
        this.value = VAL;
    }
}
