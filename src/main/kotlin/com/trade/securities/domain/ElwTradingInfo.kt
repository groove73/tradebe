package com.trade.securities.domain

data class ElwTradingInfo(
    val basDd: String? = null, // 기준일자
    val isuCd: String? = null, // 종목코드
    val isuNm: String? = null, // 종목명
    val tddClsprc: String? = null, // 종가
    val cmpprevddPrc: String? = null, // 대비
    val tddOpnprc: String? = null, // 시가
    val tddHgprc: String? = null, // 고가
    val tddLwprc: String? = null, // 저가
    val accTrdvol: String? = null, // 거래량
    val accTrdval: String? = null, // 거래대금
    val mktcap: String? = null, // 시가총액
    val listShrs: String? = null, // 상장증권수
    val ulyNm: String? = null, // 기초자산 지산명
    val ulyPrc: String? = null, // 기초자산 종가
    val cmpprevddPrcUly: String? = null, // 기초자산 대비
    val flucRtUly: String? = null // 기초자산 등락률
)
