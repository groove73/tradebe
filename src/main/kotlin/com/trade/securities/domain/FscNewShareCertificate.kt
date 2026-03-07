package com.trade.securities.domain

data class FscNewShareCertificate(
    val basDt: String? = null, // 기준일자
    val srtCd: String? = null, // 단축코드
    val isinCd: String? = null, // ISIN코드
    val itmsNm: String? = null, // 종목명
    val mrktCtg: String? = null, // 시장구분
    val clpr: String? = null, // 종가
    val vs: String? = null, // 대비
    val fltRt: String? = null, // 등락률
    val mkp: String? = null, // 시가
    val hipr: String? = null, // 고가
    val lopr: String? = null, // 저가
    val trqu: String? = null, // 거래량
    val trPrc: String? = null, // 거래대금
    val lstDt: String? = null, // 상장일자
    val delstDt: String? = null, // 상장폐지일자
    val stckSrtnCd: String? = null, // 주식단축코드
    val stckItmsNm: String? = null, // 주식종목명
    val stckClpr: String? = null // 주식종가
)
