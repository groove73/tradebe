package com.trade.securities.domain

data class DerivativesTradingInfo(
    val basDd: String? = null, // 기준일자
    val prodNm: String? = null, // 상품구분
    val mktNm: String? = null, // 시장구분 (정규/야간)
    val isuCd: String? = null, // 종목코드
    val isuNm: String? = null, // 종목명
    val tddClsprc: String? = null, // 종가
    val cmpprevddPrc: String? = null, // 대비
    val tddOpnprc: String? = null, // 시가
    val tddHgprc: String? = null, // 고가
    val tddLwprc: String? = null, // 저가
    val accTrdvol: String? = null, // 거래량
    val accTrdval: String? = null, // 거래대금
    val accOpnintQty: String? = null, // 미결제약정
    val spotPrc: String? = null, // 현물가
    val setlPrc: String? = null, // 정산가
    val rghtTpNm: String? = null, // 권리유형 (CALL/PUT)
    val impVolt: String? = null, // 내재변동성
    val nxtdBasPrc: String? = null // 익일정산가
)
