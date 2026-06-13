package com.trade.securities.domain

data class BondTradingInfo(
    val basDd: String? = null, // 기준일자
    val mktNm: String? = null, // 시장구분
    val isuCd: String? = null, // 종목코드
    val isuNm: String? = null, // 종목명
    val clsprc: String? = null, // 종가 가격
    val cmpprevddPrc: String? = null, // 종가 대비
    val clsprcYd: String? = null, // 종가 수익률
    val opnprc: String? = null, // 시가 가격
    val opnprcYd: String? = null, // 시가 수익률
    val hgprc: String? = null, // 고가 가격
    val hgprcYd: String? = null, // 고가 수익률
    val lwprc: String? = null, // 저가 가격
    val lwprcYd: String? = null, // 저가 수익률
    val accTrdvol: String? = null, // 거래량
    val accTrdval: String? = null, // 거래대금
    val mktcap: String? = null, // 시가총액
    val listShrs: String? = null, // 상장증권수
    val bndExpTpNm: String? = null, // 만기년수
    val govbndIsuTpNm: String? = null // 종목구분
)
