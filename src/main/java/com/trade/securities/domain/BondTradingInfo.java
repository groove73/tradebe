package com.trade.securities.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BondTradingInfo {
    private String basDd; // 기준일자
    private String mktNm; // 시장구분
    private String isuCd; // 종목코드
    private String isuNm; // 종목명
    private String clsprc; // 종가 가격
    private String cmpprevddPrc; // 종가 대비
    private String clsprcYd; // 종가 수익률
    private String opnprc; // 시가 가격
    private String opnprcYd; // 시가 수익률
    private String hgprc; // 고가 가격
    private String hgprcYd; // 고가 수익률
    private String lwprc; // 저가 가격
    private String lwprcYd; // 저가 수익률
    private String accTrdvol; // 거래량
    private String accTrdval; // 거래대금
    private String mktcap; // 시가총액
    private String listShrs; // 상장증권수

    // 국채전문시장 전용 필드
    private String bndExpTpNm; // 만기년수
    private String govbndIsuTpNm; // 종목구분
}
