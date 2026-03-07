package com.trade.securities.domain

data class CommodityTradingInfo(
    val basDd: String? = null,
    val isuCd: String? = null,
    val isuNm: String? = null,
    val accTrdvol: String? = null,
    val accTrdval: String? = null,
    val tddClsprc: String? = null,
    val cmpprevddPrc: String? = null,
    val flucRt: String? = null,
    val tddOpnprc: String? = null,
    val tddHgprc: String? = null,
    val tddLwprc: String? = null,
    val oilNm: String? = null,
    val wtAvgPrc: String? = null,
    val wtDisAvgPrc: String? = null
)
