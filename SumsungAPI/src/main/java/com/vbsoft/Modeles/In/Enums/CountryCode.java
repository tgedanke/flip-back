package com.vbsoft.Modeles.In.Enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Код страны.
 *
 * @author vd.zinovev
 * @version 1.0
 * @since 1.0
 */
public enum CountryCode {

    @JsonProperty("AD")
    Andorra("Andorra"),
    @JsonProperty("AE")
    UtdArabEmir("United Arab Emirates"),
    @JsonProperty("AF")
    Afghanistan("Afghanistan"),
    @JsonProperty("AG")
    Antigua_Barbuda("Antigua and Barbuda"),
    @JsonProperty("AI")
    Anguilla("Anguilla"),
    @JsonProperty("AL")
    Albania("Albania"),
    @JsonProperty("AM")
    Armenia("Armenia"),
    @JsonProperty("AN")
    DutchAntilles("Dutch Antilles"),
    @JsonProperty("AO")
    Angola("Angola"),
    @JsonProperty("AQ")
    Antarctica("Antarctica"),
    @JsonProperty("AR")
    Argentina("Argentina"),
    @JsonProperty("AS")
    SamoaAmerica("American Samoa"),
    @JsonProperty("AT")
    Austria("Austria"),
    @JsonProperty("AU")
    Australia("Australia"),
    @JsonProperty("AW")
    Aruba("Aruba"),
    @JsonProperty("AZ")
    Azerbaijan("Azerbaijan"),
    @JsonProperty("BA")
    Bosnia_Herz("Bosnia and Herzegovina"),
    @JsonProperty("BB")
    Barbados("Barbados"),
    @JsonProperty("BD")
    Bangladesh("Bangladesh"),
    @JsonProperty("BE")
    Belgium("Belgium"),
    @JsonProperty("BF")
    BurkinaFaso("Burkina Faso"),
    @JsonProperty("BG")
    Bulgaria("Bulgaria"),
    @JsonProperty("BH")
    Bahrain("Bahrain"),
    @JsonProperty("BI")
    Burundi("Burundi"),
    @JsonProperty("BJ")
    Benin("Benin"),
    @JsonProperty("BL")
    Blue("Blue"),
    @JsonProperty("BM")
    Bermuda("Bermuda"),
    @JsonProperty("BN")
    BruneiDaruss("Brunei Darussalam"),
    @JsonProperty("BO")
    Bolivia("Bolivia"),
    @JsonProperty("BR")
    Brazil("Brazil"),
    @JsonProperty("BS")
    Bahamas("Bahamas"),
    @JsonProperty("BT")
    Bhutan("Bhutan"),
    @JsonProperty("BV")
    BouvetIslands("Bouvet Islands"),
    @JsonProperty("BW")
    Botswana("Botswana"),
    @JsonProperty("BY")
    Belarus("Belarus"),
    @JsonProperty("BZ")
    Belize("Belize"),
    @JsonProperty("C2")
    CanaryIsland("Canary Island"),
    @JsonProperty("C3")
    CURACAO("CURACAO"),
    @JsonProperty("CA")
    Canada("Canada"),
    @JsonProperty("CC")
    CoconutIslands("Coconut Islands"),
    @JsonProperty("CD")
    Dem_Rep_Congo("Democratic Republic of the Congo"),
    @JsonProperty("CF")
    CAR("Central African Republic"),
    @JsonProperty("CG")
    Rep_Of_Congo("Republic of the Congo"),
    @JsonProperty("CH")
    Switzerland("Switzerland"),
    @JsonProperty("CI")
    Coted_d_Ivoire("Cote d'Ivoire"),
    @JsonProperty("CK")
    CookIslands("Cook Islands"),
    @JsonProperty("CL")
    Chile("Chile"),
    @JsonProperty("CM")
    Cameroon("Cameroon"),
    @JsonProperty("CN")
    China("China"),
    @JsonProperty("CO")
    Colombia("Colombia"),
    @JsonProperty("CR")
    CostaRica("Costa Rica"),
    @JsonProperty("CS")
    Serbia_Monten("Serbia and Montenegro"),
    @JsonProperty("CU")
    Cuba("Cuba"),
    @JsonProperty("CV")
    CapeVerde("Cape Verde"),
    @JsonProperty("CX")
    ChristmasIsland("Christmas Island"),
    @JsonProperty("CY")
    Cyprus("Cyprus"),
    @JsonProperty("CZ")
    CzechRepublic("Czech Republic"),
    @JsonProperty("DE")
    Germany("Germany"),
    @JsonProperty("DJ")
    Djibouti("Djibouti"),
    @JsonProperty("DK")
    Denmark("Denmark"),
    @JsonProperty("DM")
    Dominica("Dominica"),
    @JsonProperty("DO")
    DominicanRep("Dominican Republic"),
    @JsonProperty("DZ")
    Algeria("Algeria"),
    @JsonProperty("EC")
    Ecuador("Ecuador"),
    @JsonProperty("EE")
    Estonia("Estonia"),
    @JsonProperty("EG")
    Egypt("Egypt"),
    @JsonProperty("EH")
    WestSahara("West Sahara"),
    @JsonProperty("ER")
    Eritrea("Eritrea"),
    @JsonProperty("ES")
    Spain("Spain"),
    @JsonProperty("ET")
    Ethiopia("Ethiopia"),
    @JsonProperty("EU")
    EuropeanUnion("European Union"),
    @JsonProperty("FI")
    Finland("Finland"),
    @JsonProperty("FJ")
    Fiji("Fiji"),
    @JsonProperty("FK")
    FalklandIslands("Falkland Islands"),
    @JsonProperty("FM")
    Micronesia("Micronesia"),
    @JsonProperty("FO")
    FaroeIslands("Faroe Islands"),
    @JsonProperty("FR")
    France("France"),
    @JsonProperty("GA")
    Gabon("Gabon"),
    @JsonProperty("GB")
    UnitedKingdom("United Kingdom"),
    @JsonProperty("GD")
    Grenada("Grenada"),
    @JsonProperty("GE")
    Georgia("Georgia"),
    @JsonProperty("GF")
    FrenchGuayana("French Guayana"),
    @JsonProperty("GH")
    Ghana("Ghana"),
    @JsonProperty("GI")
    Gibraltar("Gibraltar"),
    @JsonProperty("GL")
    Greenland("Greenland"),
    @JsonProperty("GM")
    Gambia("Gambia"),
    @JsonProperty("GN")
    Guinea("Guinea"),
    @JsonProperty("GP")
    Guadeloupe("Guadeloupe"),
    @JsonProperty("GQ")
    EquatorialGuin("Equatorial Guin"),
    @JsonProperty("GR")
    Greece("Greece"),
    @JsonProperty("GS")
    S_SandwichIns("South Georgia and the Southern Sandwich Islands"),
    @JsonProperty("GT")
    Guatemala("Guatemala"),
    @JsonProperty("GU")
    Guam("Guam"),
    @JsonProperty("GW")
    Guinea_Bissau("Guinea-Bissau"),
    @JsonProperty("GY")
    Guyana("Guyana"),
    @JsonProperty("HK")
    HongKong("Hong Kong"),
    @JsonProperty("HM")
    Heard_McDon_Isl("Heard and McDonald Islands"),
    @JsonProperty("HN")
    Honduras("Honduras"),
    @JsonProperty("HR")
    Croatia("Croatia"),
    @JsonProperty("HT")
    Haiti("Haiti"),
    @JsonProperty("HU")
    Hungary("Hungary"),
    @JsonProperty("ID")
    Indonesia("Indonesia"),
    @JsonProperty("IE")
    Ireland("Ireland"),
    @JsonProperty("IL")
    Israel("Israel"),
    @JsonProperty("IN")
    India("India"),
    @JsonProperty("IO")
    Brit_Ind_Oc_Ter("British Indian Ocean Territory"),
    @JsonProperty("IQ")
    Iraq("Iraq"),
    @JsonProperty("IR")
    Iran("Iran"),
    @JsonProperty("IS")
    Iceland("Iceland"),
    @JsonProperty("IT")
    Italy("Italy"),
    @JsonProperty("JM")
    Jamaica("Jamaica"),
    @JsonProperty("JO")
    Jordan("Jordan"),
    @JsonProperty("JP")
    Japan("Japan"),
    @JsonProperty("KE")
    Kenya("Kenya"),
    @JsonProperty("KG")
    Kyrgyzstan("Kyrgyzstan"),
    @JsonProperty("KH")
    Cambodia("Cambodia"),
    @JsonProperty("KI")
    Kiribati("Kiribati"),
    @JsonProperty("KM")
    Comoros("Comoros"),
    @JsonProperty("KN")
    StKitts_Nevis("Saint Kitts and Nevis"),
    @JsonProperty("KP")
    NorthKorea("North Korea"),
    @JsonProperty("KR")
    SouthKorea("South Korea"),
    @JsonProperty("KW")
    Kuwait("Kuwait"),
    @JsonProperty("KY")
    CaymanIslands("Cayman Islands"),
    @JsonProperty("KZ")
    Kazakhstan("Kazakhstan"),
    @JsonProperty("LA")
    Laos("Laos"),
    @JsonProperty("LB")
    Lebanon("Lebanon"),
    @JsonProperty("LC")
    St_Lucia("St. Lucia"),
    @JsonProperty("LI")
    Liechtenstein("Liechtenstein"),
    @JsonProperty("LK")
    SriLanka("Sri Lanka"),
    @JsonProperty("LR")
    Liberia("Liberia"),
    @JsonProperty("LS")
    Lesotho("Lesotho"),
    @JsonProperty("LT")
    Lithuania("Lithuania"),
    @JsonProperty("LU")
    Luxembourg("Luxembourg"),
    @JsonProperty("LV")
    Latvia("Latvia"),
    @JsonProperty("LY")
    Libya("Libya"),
    @JsonProperty("M4")
    MIAMI("MIAMI"),
    @JsonProperty("MA")
    Morocco("Morocco"),
    @JsonProperty("MC")
    Monaco("Monaco"),
    @JsonProperty("MD")
    Moldova("Moldova"),
    @JsonProperty("MG")
    Madagascar("Madagascar"),
    @JsonProperty("MH")
    MarshallIslands("Marshall Islands"),
    @JsonProperty("MK")
    Macedonia("Macedonia"),
    @JsonProperty("ML")
    Mali("Mali"),
    @JsonProperty("MM")
    Burma("Burma"),
    @JsonProperty("MN")
    Mongolia("Mongolia"),
    @JsonProperty("MO")
    Macau("Macau"),
    @JsonProperty("MP")
    N_MarianaIsland("North Mariana Islands"),
    @JsonProperty("MQ")
    Martinique("Martinique"),
    @JsonProperty("MR")
    Mauretania("Mauretania"),
    @JsonProperty("MS")
    Montserrat("Montserrat"),
    @JsonProperty("MT")
    Malta("Malta"),
    @JsonProperty("MU")
    Mauritius("Mauritius"),
    @JsonProperty("MV")
    Maldives("Maldives"),
    @JsonProperty("MW")
    Malawi("Malawi"),
    @JsonProperty("MX")
    Mexico("Mexico"),
    @JsonProperty("MY")
    Malaysia("Malaysia"),
    @JsonProperty("MZ")
    Mozambique("Mozambique"),
    @JsonProperty("NA")
    Namibia("Namibia"),
    @JsonProperty("NC")
    NewCaledonia("New Caledonia"),
    @JsonProperty("NE")
    Niger("Niger"),
    @JsonProperty("NF")
    NorfolkIslands("Norfolk Islands"),
    @JsonProperty("NG")
    Nigeria("Nigeria"),
    @JsonProperty("NI")
    Nicaragua("Nicaragua"),
    @JsonProperty("NL")
    Netherlands("Netherlands"),
    @JsonProperty("NO")
    Norway("Norway"),
    @JsonProperty("NP")
    Nepal("Nepal"),
    @JsonProperty("NR")
    Nauru("Nauru"),
    @JsonProperty("NT")
    NATO("NATO"),
    @JsonProperty("NU")
    Niue("Niue"),
    @JsonProperty("NZ")
    NewZealand("New Zealand"),
    @JsonProperty("OM")
    Oman("Oman"),
    @JsonProperty("OR")
    Orange("Orange"),
    @JsonProperty("PA")
    Panama("Panama"),
    @JsonProperty("PE")
    Peru("Peru"),
    @JsonProperty("PF")
    Frenc_Polynesia("French Polynesia"),
    @JsonProperty("PG")
    Pap_NewGuinea("Papua New Guinea"),
    @JsonProperty("PH")
    Philippines("Philippines"),
    @JsonProperty("PK")
    Pakistan("Pakistan"),
    @JsonProperty("PL")
    Poland("Poland"),
    @JsonProperty("PM")
    St_Pier_Miquel("St. Pierre and Miquelon"),
    @JsonProperty("PN")
    PitcairnIslands("Pitcair nIslands"),
    @JsonProperty("PR")
    PuertoRico("Puerto Rico"),
    @JsonProperty("PS")
    Palestine("Palestine"),
    @JsonProperty("PT")
    Portugal("Portugal"),
    @JsonProperty("PW")
    Palau("Palau"),
    @JsonProperty("PY")
    Paraguay("Paraguay"),
    @JsonProperty("QA")
    Qatar("Qatar"),
    @JsonProperty("RE")
    Reunion("Reunion"),
    @JsonProperty("RO")
    Romania("Romania"),
    @JsonProperty("RU")
    RussianFed("Russian Federation"),
    @JsonProperty("RW")
    Rwanda("Rwanda"),
    @JsonProperty("S1")
    SAIPAN("SAIPAN"),
    @JsonProperty("SA")
    SaudiArabia("Saudi Arabia"),
    @JsonProperty("SB")
    SolomonIslands("Solomon Islands"),
    @JsonProperty("SC")
    Seychelles("Seychelles"),
    @JsonProperty("SD")
    Sudan("Sudan"),
    @JsonProperty("SE")
    Sweden("Sweden"),
    @JsonProperty("SG")
    Singapore("Singapore"),
    @JsonProperty("SH")
    SaintHelena("Saint Helena"),
    @JsonProperty("SI")
    Slovenia("Slovenia"),
    @JsonProperty("SJ")
    Svalbard("Svalbard"),
    @JsonProperty("SK")
    Slovakia("Slovakia"),
    @JsonProperty("SL")
    SierraLeone("Sierra Leone"),
    @JsonProperty("SM")
    SanMarino("San Marino"),
    @JsonProperty("SN")
    Senegal("Senegal"),
    @JsonProperty("SO")
    Somalia("Somalia"),
    @JsonProperty("SR")
    Suriname("Suriname"),
    @JsonProperty("ST")
    S_Tome_Principe("Sao Tome and Principe"),
    @JsonProperty("SV")
    ElSalvador("El Salvador"),
    @JsonProperty("SY")
    Syria("Syria"),
    @JsonProperty("SZ")
    Swaziland("Swaziland"),
    @JsonProperty("T1")
    TAHITI("TAHITI"),
    @JsonProperty("TC")
    TurkshCaicosin("Turks and Caicos Islands"),
    @JsonProperty("TD")
    Chad("Chad"),
    @JsonProperty("TF")
    French_S_Territ("French S.Territ"),
    @JsonProperty("TG")
    Togo("Togo"),
    @JsonProperty("TH")
    Thailand("Thailand"),
    @JsonProperty("TJ")
    Tajikistan("Tajikistan"),
    @JsonProperty("TK")
    TokelauIslands("Tokelau Islands"),
    @JsonProperty("TL")
    EastTimor("East Timor"),
    @JsonProperty("TM")
    Turkmenistan("Turkmenistan"),
    @JsonProperty("TN")
    Tunisia("Tunisia"),
    @JsonProperty("TO")
    Tonga("Tonga"),
    @JsonProperty("TP")
    East_Timor("East Timor"),
    @JsonProperty("TR")
    Turkey("Turkey"),
    @JsonProperty("TT")
    Trinidad_Tobago("Trinidad and Tobago"),
    @JsonProperty("TV")
    Tuvalu("Tuvalu"),
    @JsonProperty("TW")
    Taiwan("Taiwan"),
    @JsonProperty("TZ")
    Tanzania("Tanzania"),
    @JsonProperty("UA")
    Ukraine("Ukraine"),
    @JsonProperty("UG")
    Uganda("Uganda"),
    @JsonProperty("UM")
    MinorOutl_Isl("American Minor Outlying Islands"),
    @JsonProperty("UN")
    UnitedNations("United Nations"),
    @JsonProperty("US")
    USA("USA"),
    @JsonProperty("UY")
    Uruguay("Uruguay"),
    @JsonProperty("UZ")
    Uzbekistan("Uzbekistan"),
    @JsonProperty("VA")
    VaticanCity("Vatican City"),
    @JsonProperty("VC")
    St_Vincent("St. Vincent and the Grenadines"),
    @JsonProperty("VE")
    Venezuela("Venezuela"),
    @JsonProperty("VG")
    Brit_Virgin_Is("British Virgin Islands"),
    @JsonProperty("VI")
    Amer_Virgin_Is("American Virgin Islands"),
    @JsonProperty("VN")
    Vietnam("Vietnam"),
    @JsonProperty("VU")
    Vanuatu("Vanuatu"),
    @JsonProperty("WF")
    Wallis_Futuna("Wallis and Futuna Islands"),
    @JsonProperty("WS")
    Samoa("Samoa"),
    @JsonProperty("YE")
    Yemen("Yemen"),
    @JsonProperty("YT")
    Mayotte("Mayotte"),
    @JsonProperty("ZA")
    SouthAfrica("South Africa"),
    @JsonProperty("ZM")
    Zambia("Zambia"),
    @JsonProperty("ZW")
    Zimbabwe("Zimbabwe"),
    @JsonProperty("AX")
    AlandIslands("Aland Islands"),
    @JsonProperty("GG")
    Guernsey("Guernsey"),
    @JsonProperty("IM")
    IsleOfMan("Isle Of Man"),
    @JsonProperty("JE")
    Jersey("Jersey"),
    @JsonProperty("ME")
    Montenegro("Montenegro"),
    @JsonProperty("RS")
    Serbia("Serbia");

    /**
     * Значение перечисления.
     */
    @Getter
    private final String value;

    /**
     * Конструктор.
     *
     * @param VAL Значение перечисления.
     */
    CountryCode(String VAL) {
        this.value = VAL;
    }
}
