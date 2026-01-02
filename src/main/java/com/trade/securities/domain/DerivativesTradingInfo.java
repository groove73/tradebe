package com.trade.securities.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DerivativesTradingInfo {
    private String basDd; // 기준일자
    private String prodNm; // 상품구분
    private String mktNm; // 시장구분 (정규/야간)
    private String isuCd; // 종목코드
    private String isuNm; // 종목명
    private String tddClsprc; // 종가
    private String cmpprevddPrc; // 대비
    private String tddOpnprc; // 시가
    private String tddHgprc; // 고가
    private String tddLwprc; // 저가
    private String accTrdvol; // 거래량
    private String accTrdval; // 거래대금
    private String accOpnintQty; // 미결제약정

    // 선물 전용
    private String spotPrc; // 현물가
    private String setlPrc; // 정산가

    // 옵션 전용
    private String rghtTpNm; // 권리유형 (CALL/PUT)
    private String impVolt; // 내재변동성
    private String nxtdBasPrc; // 익일정산가
}
