package com.trade.securities.domain

data class EtfTradingInfo(
    val basDd: String? = null, // 기준일자
    val isuCd: String? = null, // 종목코드
    val isuNm: String? = null, // 종목명
    val tddClsprc: String? = null, // 종가
    val cmpprevddPrc: String? = null, // 대비
    val flucRt: String? = null, // 등락률
    val nav: String? = null, // 순자산가치(NAV)
    val tddOpnprc: String? = null, // 시가
    val tddHgprc: String? = null, // 고가
    val tddLwprc: String? = null, // 저가
    val accTrdvol: String? = null, // 거래량
    val accTrdval: String? = null, // 거래대금
    val mktcap: String? = null, // 시가총액
    val invstasstNetasstTotamt: String? = null, // 순자산총액
    val listShrs: String? = null, // 상장주식수
    val idxIndNm: String? = null, // 기초지수 지수명
    val objStkprcIdx: String? = null, // 기초지수 종가
    val cmpprevddIdx: String? = null, // 기초지수 대비
    val flucRtIdx: String? = null // 기초지수 등락률
)
