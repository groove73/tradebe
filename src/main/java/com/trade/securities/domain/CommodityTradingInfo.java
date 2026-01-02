package com.trade.securities.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommodityTradingInfo {
    private String basDd;
    private String isuCd;
    private String isuNm;

    // Joint fields
    private String accTrdvol;
    private String accTrdval;

    // Gold & Emissions spec
    private String tddClsprc;
    private String cmpprevddPrc;
    private String flucRt;
    private String tddOpnprc;
    private String tddHgprc;
    private String tddLwprc;

    // Oil spec
    private String oilNm;
    private String wtAvgPrc;
    private String wtDisAvgPrc;
}
