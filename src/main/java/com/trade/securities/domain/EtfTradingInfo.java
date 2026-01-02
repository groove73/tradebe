package com.trade.securities.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EtfTradingInfo {
    private String basDd; // 기준일자
    private String isuCd; // 종목코드
    private String isuNm; // 종목명
    private String tddClsprc; // 종가
    private String cmpprevddPrc; // 대비
    private String flucRt; // 등락률
    private String nav; // 순자산가치(NAV)
    private String tddOpnprc; // 시가
    private String tddHgprc; // 고가
    private String tddLwprc; // 저가
    private String accTrdvol; // 거래량
    private String accTrdval; // 거래대금
    private String mktcap; // 시가총액
    private String invstasstNetasstTotamt; // 순자산총액
    private String listShrs; // 상장주식수
    private String idxIndNm; // 기초지수 지수명
    private String objStkprcIdx; // 기초지수 종가
    private String cmpprevddIdx; // 기초지수 대비
    private String flucRtIdx; // 기초지수 등락률
}
