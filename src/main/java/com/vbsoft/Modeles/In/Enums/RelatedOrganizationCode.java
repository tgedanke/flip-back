package com.vbsoft.Modeles.In.Enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public enum RelatedOrganizationCode {
    @JsonProperty("AS")
    ApplicantWarranty("Applicant Warranty"),
    @JsonProperty("TV")
    InsuranceT("Insurance (T.)"),
    @JsonProperty("ZB")
    VendorCust_Broke("Vendor(Cust. broke)"),
    @JsonProperty("RE")
    Bill_To_Party("Bill-to party"),
    @JsonProperty("EK")
    Buyer("Buyer"),
    @JsonProperty("ZC")
    Vendor_Carrier("Vendor(Carrier)"),
    @JsonProperty("Z4")
    CONSIGNEE("CONSIGNEE"),
    @JsonProperty("ZA")
    End_Customer("End-Customer"),
    @JsonProperty("Z5")
    CONTRACTING_PARTY("CONTRACTING_PARTY"),
    @JsonProperty("ZF")
    Vendor_Forwarder("Vendor(Forwarder)"),
    @JsonProperty("ZI")
    Vendor_InsuComp("Vendor(Insu. Comp)"),
    @JsonProperty("Z9")
    LIABLE_PARTY("LIABLE_PARTY"),
    @JsonProperty("MP")
    Mail_Partner("Mail partner"),
    @JsonProperty("KU")
    Coordinator_User("Coordinator (user)"),
    @JsonProperty("Z6")
    NOTIFY_PARTY("NOTIFY_PARTY"),
    @JsonProperty("Z7")
    ALSO_NOTIFY_PARTY("ALSO_NOTIFY_PARTY"),
    @JsonProperty("Z8")
    NOTIFY_PARTY3("NOTIFY_PARTY 3"),
    @JsonProperty("SE")
    Sender("Sender"),
    @JsonProperty("AG")
    Sold_To_Party("Sold-to party"),
    @JsonProperty("WE")
    Ship_To_Party("Ship-to party"),
    @JsonProperty("ZT")
    Vendor_TruckComp("Vendor(Truck Comp)"),
    @JsonProperty("RI")
    ReinsuranceCompany("Reinsurance Company"),
    @JsonProperty("IR")
    Insurance_broker("Insurance broker"),
    @JsonProperty("RG")
    Payer("Payer"),
    @JsonProperty("Z3")
    Vendor_3PL("Vendor(3PL)"),
    @JsonProperty("LF")
    Vendor("Vendor"),
    @JsonProperty("ZO")
    OEM_CUSTOMER("OEM CUSTOMER"),
    @JsonProperty("ZE")
    FINAL_CUSTOMER("FINAL CUSTOMER"),
    @JsonProperty("VE")
    Employee("Employee"),
    @JsonProperty("SH")
    Shipper("Shipper"),
    @JsonProperty("SP")
    SP("Shipper");

    @Getter
    private final String value;

    RelatedOrganizationCode(final String VAL) {
        this.value = VAL;
    }
}
