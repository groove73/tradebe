package com.trade.securities.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FscStockPrice {
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
    private String lstgStCnt; // 상장주식수
    private String mrktTotAmt; // 시가총액
}
