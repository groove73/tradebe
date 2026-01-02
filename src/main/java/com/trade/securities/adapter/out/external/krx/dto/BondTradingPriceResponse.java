package com.trade.securities.adapter.out.external.krx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trade.securities.domain.BondTradingInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class BondTradingPriceResponse {

    @JsonProperty("OutBlock_1")
    private List<OutBlock> outBlock1;

    @Getter
    @NoArgsConstructor
    public static class OutBlock {
        @JsonProperty("BAS_DD")
        private String basDd;
        @JsonProperty("MKT_NM")
        private String mktNm;
        @JsonProperty("ISU_CD")
        private String isuCd;
        @JsonProperty("ISU_NM")
        private String isuNm;
        @JsonProperty("CLSPRC")
        private String clsprc;
        @JsonProperty("CMPPREVDD_PRC")
        private String cmpprevddPrc;
        @JsonProperty("CLSPRC_YD")
        private String clsprcYd;
        @JsonProperty("OPNPRC")
        private String opnprc;
        @JsonProperty("OPNPRC_YD")
        private String opnprcYd;
        @JsonProperty("HGPRC")
        private String hgprc;
        @JsonProperty("HGPRC_YD")
        private String hgprcYd;
        @JsonProperty("LWPRC")
        private String lwprc;
        @JsonProperty("LWPRC_YD")
        private String lwprcYd;
        @JsonProperty("ACC_TRDVOL")
        private String accTrdvol;
        @JsonProperty("ACC_TRDVAL")
        private String accTrdval;
        @JsonProperty("MKTCAP")
        private String mktcap;
        @JsonProperty("LIST_SHRS")
        private String listShrs;

        // 국채전문시장 전용 필드
        @JsonProperty("BND_EXP_TP_NM")
        private String bndExpTpNm;
        @JsonProperty("GOVBND_ISU_TP_NM")
        private String govbndIsuTpNm;

        public BondTradingInfo toDomain() {
            return BondTradingInfo.builder()
                    .basDd(basDd)
                    .mktNm(mktNm)
                    .isuCd(isuCd)
                    .isuNm(isuNm)
                    .clsprc(clsprc)
                    .cmpprevddPrc(cmpprevddPrc)
                    .clsprcYd(clsprcYd)
                    .opnprc(opnprc)
                    .opnprcYd(opnprcYd)
                    .hgprc(hgprc)
                    .hgprcYd(hgprcYd)
                    .lwprc(lwprc)
                    .lwprcYd(lwprcYd)
                    .accTrdvol(accTrdvol)
                    .accTrdval(accTrdval)
                    .mktcap(mktcap)
                    .listShrs(listShrs)
                    .bndExpTpNm(bndExpTpNm)
                    .govbndIsuTpNm(govbndIsuTpNm)
                    .build();
        }
    }

    public List<BondTradingInfo> toDomainList() {
        if (outBlock1 == null)
            return List.of();
        return outBlock1.stream()
                .map(OutBlock::toDomain)
                .collect(Collectors.toList());
    }
}
