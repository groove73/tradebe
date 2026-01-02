package com.trade.securities.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElwTradingInfo {
    private String basDd; // 기준일자
    private String isuCd; // 종목코드
    private String isuNm; // 종목명
    private String tddClsprc; // 종가
    private String cmpprevddPrc; // 대비
    private String tddOpnprc; // 시가
    private String tddHgprc; // 고가
    private String tddLwprc; // 저가
    private String accTrdvol; // 거래량
    private String accTrdval; // 거래대금
    private String mktcap; // 시가총액
    private String listShrs; // 상장증권수
    private String ulyNm; // 기초자산 지산명
    private String ulyPrc; // 기초자산 종가
    private String cmpprevddPrcUly; // 기초자산 대비
    private String flucRtUly; // 기초자산 등락률
}
