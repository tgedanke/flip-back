package com.vbsoft.Modeles.In.Enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Справочник кодов "Документ основание".
 * Справочник 18 в документе CELLO_EDI Code.
 *
 * @author vd.zinovev
 * @version 1.0
 * @since 1.0
 */
public enum RelatedDocumentCode {

    @JsonProperty("001")
    ACKANS("ACKANS"),

    @JsonProperty("105")
	PurchaseOrder("Purchase Order"),

    @JsonProperty("111")
    Genres("Genres"),

    @JsonProperty("150")
    InternalTransPortOrder("Internal TransPort Order"),

    @JsonProperty("1T0")
	RefDocTypeForLogtechManualUpload("Ref Doc Type For Logtech Manual Upload"),

    @JsonProperty("23E")
	NewModelInformation("New Model Information"),

    @JsonProperty("240")
	DeliveryInstructions("Delivery Instructions"),

    @JsonProperty("241")
	DeliverySchedule("Delivery Schedule"),

    @JsonProperty("242")
	ShipmentOrderrSTD("Shipment Orderr(STD)"),

    @JsonProperty("243")
	ShipmentOrderSTDEXTI1by3PL("Shipment Order(STD, EXTI1 by 3PL)"),

    @JsonProperty("270")
	DeliveryNote("Delivery Note"),

    @JsonProperty("271")
	PackingList("Packing List"),

    @JsonProperty("280")
	InventoryStockLevel("Inventory Stock Level"),

    @JsonProperty("2AB")
	INF700("INF700(Documentary Credit Issuan"),

    @JsonProperty("315")
    Contract("Contract"),

    @JsonProperty("31B")
    ShipmentExternalIDForADC("Shipment External ID For ADC"),

    @JsonProperty("31C")
    ShipmentNoForADCInbound("Shipment No For ADC Inbound"),

    @JsonProperty("335")
    BookingRequest("Booking Request"),

    @JsonProperty("340")
    ShippingRequest("Shipping Request"),

    @JsonProperty("380")
    CommercialInvoice("Commercial Invoice"),

    @JsonProperty("390")
    ImportInvoice("Import Invoice"),

    @JsonProperty("3HB")
    EntryFeeCustomsClearance("Entry Fee(Customs Clearance)"),

    @JsonProperty("3ID")
	IDocForSSI("IDoc for SSI"),

    @JsonProperty("3PD")
	PedimentoNo("Pedimento no."),

    @JsonProperty("3SV")
	ServiceRequest("Service Request(ADC)"),

    @JsonProperty("454")
	CreditAdvice("Service Request(ADC)"),

    @JsonProperty("456")
	DebitAdvice("Debit Advice"),

    @JsonProperty("460")
	DocumentaryCreditApplication("Documentary Credit Application"),

    @JsonProperty("492")
	FinancialStatementOfAccount("Financial Statement of Account"),

    @JsonProperty("4AA")
	InsuranceApplication("Insurance Application"),

    @JsonProperty("4AB")
	CargoInsurancePolicyAdvice("Cargo Insurance Policy Advice"),

    @JsonProperty("520")
	InsuranceCertification("Insurance Certification"),

    @JsonProperty("575")
	InsuranceInvoice("Insurance Invoice"),

    @JsonProperty("580")
    YYYYMMOP("[Insurance] Monthly Insurance(YYYYMM-OP No.)"),

    @JsonProperty("581")
    YYYYMMBAOP("[Insurance] YYYYMM+B/A+OP No"),

    @JsonProperty("582")
    YYYYMMBA("[Insurance] YYYYMM-B/A For Manualiy Upload without Material Info"),

    @JsonProperty("583")
    YYYYMMOPCredit("[Insurance] Monthly Insurance(YYYYMM-OP No.) For Credit"),

    @JsonProperty("584")
    YYYYMMBAOPCredit("[Insurance] YYYYMM+B/A+OP No For Credit"),

    @JsonProperty("585")
    PaymentNoForNetworkDiv("Payment No. for Network div.(C100)"),

    @JsonProperty("590")
    InsurPaymentNoForNetworkDiv("Insur.Payment No. for Network div.(C100)"),

    @JsonProperty("5AA")
    ExportDelcarationAdvice("Export Delcaration Advice"),

    @JsonProperty("5DT")
	StockTransferResult("Stock Transfer Result"),

    @JsonProperty("61E")
	ShipmentStatusInformation("Shipment Status Information"),

    @JsonProperty("631")
	ConsolGRInformationEDI("Consol - GR Information(EDI)"),

    @JsonProperty("632")
	GRInformationEDI("GR Information(EDI)"),

    @JsonProperty("633")
	GoodsReceipt("Goods Receipt(STD-TTSEC-TSED)"),

    @JsonProperty("640")
	InboundDeliveryRequest("Inbound Delivery Request(EDI)"),

    @JsonProperty("641")
	OutboundDeliveryOrderSTD("Outbound Delivery Order(STD)"),

    @JsonProperty("642")
    OutboundDeliveryOrderEDI("Outbound Delivery Order(EDI)"),

    @JsonProperty("645")
	HandlingUnitNo("Handling Unit No."),

    @JsonProperty("646")
	ExternalHandlingUnitIdentification("External Handling Unit Identification"),

    @JsonProperty("648")
	ConsolidationNoLSP_FREINV("Consolidation No(LSP) - FREINV"),

    @JsonProperty("649")
	ConsolidationNoOfIDR("Consolidation No Of IDR(R2-China)"),

    @JsonProperty("650")
    CMS_STB("CMS - STB"),

    @JsonProperty("655")
	GIInformation("GI Information"),

    @JsonProperty("661")
	InboundDeliveryNo("Inbound Delivery No(STD)"),

    @JsonProperty("662")
	VendorInvoice("Vendor Invoice(IDR)"),

    @JsonProperty("670")
	NotaFiscal("Nota fiscal"),

    @JsonProperty("6DT")
	DeliveryTrackingCodeForADC("Delivery(10) + '-' + Tracking Code for ADC"),

    @JsonProperty("6LD")
	LoadID("Load ID"),

    @JsonProperty("6NB")
	StockTransferOrder("Stock Transfer Order"),

    @JsonProperty("6PD")
	Pedimento("Pedimento"),

    @JsonProperty("700")
	HouseBL_SA("House BL(SA)"),

    @JsonProperty("701")
	HouseBL_IDO("House BL(IDO)"),

    @JsonProperty("702")
    HouseBL_BWT("House BL(BWT)"),

    @JsonProperty("703")
    HouseBL("House BL"),

    @JsonProperty("705")
	MasterBL("Master BL"),

    @JsonProperty("707")
	BLAdvice_EDI("BL Advice(EDI)"),

    @JsonProperty("708")
	HouseBL_ZTMMV00070("House BL(ZTMMV00070)"),

    @JsonProperty("711")
	ConsolidationNo("Consolidation No(Shipment)"),

    @JsonProperty("712")
	TPLShipmentID2_IDOC_STPPOD("TPL Shipment ID 2 - IDOC(STPPOD)"),


    @JsonProperty("713")
	TPLShipmentID_IDOC_WHSCON("TPL Shipment ID - IDOC(WHSCON)"),

    @JsonProperty("714")
	TPLShipmentID1Return_IDOC_STPPOD("TPL Shipment ID 1(Return) - IDOC(STPPOD)"),

    @JsonProperty("715")
	TPLShipmentID2Return_IDOC_STPPOD("TPL Shipment ID 2(Return) - IDOC(STPPOD)"),

    @JsonProperty("716")
	InboundProcessID("Inbound Process ID"),

    @JsonProperty("717")
	UPSTrackingNo_Service("UPS Tracking No.(Service)"),

    @JsonProperty("718")
    UPSTrackingNo_Semi("UPS Tracking No.(Semi)"),

    @JsonProperty("719")
    TrackingNo_Service("Tracking No.(Service)"),

    @JsonProperty("720")
    InboundJobNo_ZTMMDL0020("Inbound Job No(ZTMMDL0020)"),

    @JsonProperty("721")
	InboundJobNo_ContainerNo("Inbound Job No+Container No"),

    @JsonProperty("730")
	MasterBL_SA("Master BL(SA)"),

    @JsonProperty("733")
	TRKListConsolNo("TRKList - Consol No"),

    @JsonProperty("770")
	BookingConfirmation("Booking Confirmation"),

    @JsonProperty("780")
	FreightInvoice("Freight Invoice"),

    @JsonProperty("781")
	ArrivalNotice_EDI("Arrival Notice(EDI)"),

    @JsonProperty("782")
	ShippingNotification_ASN("Shipping Notification(ASN)"),

    @JsonProperty("801")
	TPLFreightInvoice("TPL Freight Invoice"),

    @JsonProperty("901")
	ProofOfDelivery("Proof Of Delivery"),

    @JsonProperty("950")
	Daily_YYYYMMDD("Daily(YYYYMMDD)"),

    @JsonProperty("951")
	Monthly_YYYYMMDD("Monthly(YYYYMM)"),

    @JsonProperty("952")
	Weekly_YYYYMMDD("Weekly(YYYYWW)"),

    @JsonProperty("953")
	FstOfMonth_YYYYMMDD01("1st of Month(YYYYMM01)"),

    @JsonProperty("954")
    SndOfMonthYYYYMMDD_02("2nd of Month(YYYYMM02)"),

    @JsonProperty("955")
	PlantCode_200803_P620("YYYYMM-Plant code (ex>200803-P620)"),

    @JsonProperty("956")
	PlantCode20080305_P620("YYYYMMDD-Plant Code(Ex>20080305-P620)"),

    @JsonProperty("957")
	PlantCodeMaterialNo("YYYYMMDD-Plant Code-material no(Ex>20080305-P620-0203-002173)"),

    @JsonProperty("958")
	PlantCodeDivision("YYYYMM-Plant Code-Division(Ex>200803-P620-D1)"),

    @JsonProperty("959")
	BA("YYYYMM-BA(Ex>200803-AK01)"),

    @JsonProperty("960")
	PlantCodeStorageLoc("YYYYMM-Plant Code-Storage Loc,(Ex>200803-S425-WC1L)"),

    @JsonProperty("961")
    Weekly_YYYYMM_WW("Weekly(YYYYMM-WW)"),

    @JsonProperty("962")
	PlantCodeStorageLoc_WC1L("YYYYMMDD-Plant Code-Storage Loc,(Ex>20080301-S425-WC1L)"),

    @JsonProperty("963")
    ThreePLCode("YYYYMM-3PL Code(Ex>200803-XXXXXXXXXX)"),

    @JsonProperty("964")
    PlantCodeDivision_tpl("YYYYMM-Plant Code-Division-tpl(Ex>200803-P620-D1-xxxxxx)"),

    @JsonProperty("ACC")
    Accrual("Accrual"),

    @JsonProperty("BBY")
    Allowance("BBY Allowance"),

    @JsonProperty("CCC")
    CustomsClearanceNo("Customs clearance no."),

    @JsonProperty("CCD")
    CCD_NUMBER("CCD(GTD) NUMBER"),

    @JsonProperty("IT0")
    Network("Network(KO)"),

    @JsonProperty("OBS")
    InstallmentOrder("Installment Order(SEHK-MCS)"),

    @JsonProperty("OSV")
    OthersSVC("Others-SVC(SEHK-MCS)"),

    @JsonProperty("RMS")
    Return_Request("Return Request"),

    @JsonProperty("RT1")
    ReturnDelivery("Return Delivery"),

    @JsonProperty("RT2")
    ReturnFreightInvoice("Return Freight Invoice(same as 801)");

    /**
     * Enum value.
     */
    @Getter
    private final String value;

    /**
     * Enum constructor.
     * @param VAL Current enum.
     */
    RelatedDocumentCode(final String VAL) {
        this.value = VAL;
    }


}
