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
    Truck ("Truck", "CAR"),

    @JsonProperty("02")
	Mail ("Mail", "ST"),

    @JsonProperty("03")
	Rail ("Rail", "TRA"),

    @JsonProperty("04")
	Sea ("Sea", "AF"),

    @JsonProperty("05")
	Air ("Air", "AIR"),

    @JsonProperty("C1")
    FCLRail ("FCL-Rail", "TRA"),

    @JsonProperty("C2")
    LCLRail ("LCL-Rail", "TRA"),

    @JsonProperty("C3")
    RailAir ("Rail-Air", "AIR"),

    @JsonProperty("C4")
    FTLAir ("FTL-Air", "AIR"),

    @JsonProperty("C5")
    LTLAir ("LTL-Air", "AIR"),

    @JsonProperty("C6")
    FTLBarge ("FTL-Barge", "AF"),

    @JsonProperty("C7")
    LTLBarge ("LTL-Barge", "AF"),

    @JsonProperty("C8")
    SeaAir ("Sea-Air", "AF"),

    @JsonProperty("C9")
    SeaTrain ("Sea-Train", "AF"),

    @JsonProperty("F1")
    TrailerOnFlatcar ("Trailer On Flatcar", "CAR"),

    @JsonProperty("F2")
    ContainerOnFlatcar ("Container On Flatcar", "CAR"),

    @JsonProperty("M1")
    MailParcel ("Mail ( Parcel )", "ST"),

    @JsonProperty("M2")
    Express ("Express", "EX"),

    @JsonProperty("M3")
    HandCarry ("Hand Carry", "AF"),

    @JsonProperty("NT")
    NonTransport ("non-transport", "AF"),

    @JsonProperty("P1")
    Plane ("Plane", "AIR"),

    @JsonProperty("P2")
    Charter ("Charter", "AIR"),

    @JsonProperty("R1")
    Train ("Train", "TRA"),

    @JsonProperty("R2")
    ExpressTrain ("Express Train", "ICX"),

    @JsonProperty("R3")
    BlackTrain ("Black Train", "IC"),

    @JsonProperty("S1")
    ShipFCL ("Ship(FCL)", "AF"),

    @JsonProperty("S2")
    ShipLCL ("Ship(LCL)", "AF"),

    @JsonProperty("S3")
    Barge ("Barge", "AF"),

    @JsonProperty("S4")
    Ferry ("Ferry", "AF"),

    @JsonProperty("T1")
    FullTruckFTL ("Full Truck(FTL)", "CAR"),

    @JsonProperty("T2")
    ThanTruckLTL ("Le. Than Truck(LTL)", "CAR"),

    @JsonProperty("T3")
    FullContainerFCL ("Full Container(FCL)", "CAR"),

    @JsonProperty("T4")
    TnContLCL ("Le. Th. Cont.(LCL)", "AF"),

    @JsonProperty("T5")
    TeamDrivers ("Team-Drivers", "AF"),

    @JsonProperty("WM")
    WareHouseFREINVReceivingOnly ("WM	WareHouse ( FREINV receiving Only )", "AF");

    /**
     * Значение enum.
     */
    @Getter
    private final String value;

    /**
     * Ассоциация с флиппост.
     */
    @Getter
    private final String flippostValue;

    /**
     * Конструктор.
     * @param VAL Текущее значение
     */
    TransportationTypeCode(String VAL, String flipVal) {
        this.value = VAL;
        this.flippostValue = flipVal;
    }
}
