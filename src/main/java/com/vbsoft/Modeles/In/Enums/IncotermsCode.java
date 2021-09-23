package com.vbsoft.Modeles.In.Enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * INCOTERMS Code.
 *
 *  Справочник 14 в документе CELLO_EDI Code.
 * @author vd.zinovev
 * @version 1.0
 * @since 1.0
 */
public enum IncotermsCode {

    @JsonProperty("CAF")
    CAF("Cost and freight to destination"),
    @JsonProperty("CAF")
    CAI("Cost and insurance to named destination"),
    @JsonProperty("CCL")
    CCL("Customer Collect"),
    @JsonProperty("CFR")
    CFR("Cost and Freight"),
    @JsonProperty("CIF")
    CIF("Cost, insurance, freight to named destination"),
    @JsonProperty("CIN")
    CIN("Cost, insurance"),
    @JsonProperty("CIP")
    CIP("Freight, carriage, insurance to destination"),
    @JsonProperty("CPT")
    CPT("Carriage Paid to"),
    @JsonProperty("DAF")
    DAF("Delivery at frontier - named place"),
    @JsonProperty("DCP")
    DCP("Freight, carriage paid to destination"),
    @JsonProperty("DDP")
    DDP("Delivered duty paid to destination"),
    @JsonProperty("DDU")
    DDU("Delivered Duty Unpaid"),
    @JsonProperty("DEQ")
    DEQ("Delivered Ex-Quay"),
    @JsonProperty("DES")
    DES("Delivered Ex-ship"),
    @JsonProperty("EXQ")
    EXQ("Ex Quay - duty paid, named port"),
    @JsonProperty("EXS")
    EXS("Ex ship - named port of destination"),
    @JsonProperty("EXW")
    EXW("Ex works(...named place)"),
    @JsonProperty("FAS")
    FAS("Free alongside ship-named port"),
    @JsonProperty("FCA")
    FCA("Free carrier(...named place)"),
    @JsonProperty("FCI")
    FCI("Freight carriage and insurance paid to"),
    @JsonProperty("FCP")
    FCP("Freight carriage and paid to"),
    @JsonProperty("FCT")
    FCT("Freight carriage and insurance paid to"),
    @JsonProperty("FOA")
    FOA("FOB airport-named airport of departure"),
    @JsonProperty("FOB")
    FOB("Free on board-named port of shipment"),
    @JsonProperty("FOR")
    FOR("Free on rail-named departure point"),
    @JsonProperty("FOS")
    FOS("Free on board stowed named port of shipment"),
    @JsonProperty("FOT")
    FOT("Free on truck-named departure point"),
    @JsonProperty("FPC")
    FPC("Free in pipeline connection named place"),
    @JsonProperty("FRC")
    FRC("Free Carrier"),
    @JsonProperty("ZZZ")
    ZZZ("Mutually defined"),
    @JsonProperty("0CP")
    ZeroCP("Customer Pickup"),
    @JsonProperty("0MC")
    ZeroMC("SEDA BA Sales"),
    @JsonProperty("0ND")
    ZeroND("Normal Delivery (Door Delivery 1CP	Customer P/U & Inspecio"),
    @JsonProperty("3CL")
    ThreeCL("Freight Collect"),
    @JsonProperty("3EX")
    ThreeEX	("Point Of Origin"),
    @JsonProperty("3PA")
    ThreePA("Frt Prepaid & Add-FOB Dest"),
    @JsonProperty("3PB")
    ThreePB("Frt Prepaid & Add-FOB Orig"),
    @JsonProperty("3PB")
    ThreePP("Frt Prepaid & Allowed-FOB Dest"),
    @JsonProperty("3PB")
    ThreePQ("Frt Prepaid & Allowed-FOB Orig"),
    @JsonProperty("3TC")
    ThreeTC("Third Party Collect"),
    @JsonProperty("3TC")
    FourPC("Duty paid, CIF"),
    @JsonProperty("4PF")
    FourPF("Duty paid, FOB"),
    @JsonProperty("4UC")
    FourUC("Duty unpaid, CIF"),
    @JsonProperty("UN")
    UN("Not Free");

    /**
     * Текущее значение перечисления.
     */
    @Getter
    private final String value;

    /**
     * Конструктор.
     * @param VAL - Текуее значение
     */
    IncotermsCode(String VAL) {
        this.value = VAL;
    }
}
