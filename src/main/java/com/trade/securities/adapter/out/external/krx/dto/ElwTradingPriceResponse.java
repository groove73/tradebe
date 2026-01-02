package com.trade.securities.adapter.out.external.krx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trade.securities.domain.ElwTradingInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ElwTradingPriceResponse {

    @JsonProperty("OutBlock_1")
    private List<OutBlock> outBlock1;

    @Getter
    @NoArgsConstructor
    public static class OutBlock {
        @JsonProperty("BAS_DD")
        private String basDd;
        @JsonProperty("ISU_CD")
        private String isuCd;
        @JsonProperty("ISU_NM")
        private String isuNm;
        @JsonProperty("TDD_CLSPRC")
        private String tddClsprc;
        @JsonProperty("CMPPREVDD_PRC")
        private String cmpprevddPrc;
        @JsonProperty("TDD_OPNPRC")
        private String tddOpnprc;
        @JsonProperty("TDD_HGPRC")
        private String tddHgprc;
        @JsonProperty("TDD_LWPRC")
        private String tddLwprc;
        @JsonProperty("ACC_TRDVOL")
        private String accTrdvol;
        @JsonProperty("ACC_TRDVAL")
        private String accTrdval;
        @JsonProperty("MKTCAP")
        private String mktcap;
        @JsonProperty("LIST_SHRS")
        private String listShrs;
        @JsonProperty("ULY_NM")
        private String ulyNm;
        @JsonProperty("ULY_PRC")
        private String ulyPrc;
        @JsonProperty("CMPPREVDD_PRC_ULY")
        private String cmpprevddPrcUly;
        @JsonProperty("FLUC_RT_ULY")
        private String flucRtUly;

        public ElwTradingInfo toDomain() {
            return ElwTradingInfo.builder()
                    .basDd(basDd)
                    .isuCd(isuCd)
                    .isuNm(isuNm)
                    .tddClsprc(tddClsprc)
                    .cmpprevddPrc(cmpprevddPrc)
                    .tddOpnprc(tddOpnprc)
                    .tddHgprc(tddHgprc)
                    .tddLwprc(tddLwprc)
                    .accTrdvol(accTrdvol)
                    .accTrdval(accTrdval)
                    .mktcap(mktcap)
                    .listShrs(listShrs)
                    .ulyNm(ulyNm)
                    .ulyPrc(ulyPrc)
                    .cmpprevddPrcUly(cmpprevddPrcUly)
                    .flucRtUly(flucRtUly)
                    .build();
        }
    }

    public List<ElwTradingInfo> toDomainList() {
        if (outBlock1 == null)
            return List.of();
        return outBlock1.stream()
                .map(OutBlock::toDomain)
                .collect(Collectors.toList());
    }
}
