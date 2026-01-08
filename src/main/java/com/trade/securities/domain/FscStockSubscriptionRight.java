package com.trade.securities.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FscStockSubscriptionRight {
    private String basDt; // 기준일자
    private String srtCd; // 단축코드
    private String isinCd; // ISIN코드
    private String itmsNm; // 종목명
    private String mrktCtg; // 시장구분
    private String clpr; // 종가
    private String vs; // 대비
    private String fltRt; // 등락률
    private String mkp; // 시가
    private String hipr; // 고가
    private String lopr; // 저가
    private String trqu; // 거래량
    private String trPrc; // 거래대금
    private String lstDt; // 상장일자
    private String lstPnt; // 상장좌수
    private String hngpStrtDt; // 행사기준일(주식전환기 시작일)
    private String hngpEndDt; // 행사만료일(주식전환기 종료일)
    private String stckSrtnCd; // 주식단축코드
    private String stckItmsNm; // 주식종목명
    private String stckClpr; // 주식종가
}
