package com.trade.securities.adapter.out.external.krx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trade.securities.domain.EtfTradingInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class EtfTradingPriceResponse {

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
        @JsonProperty("FLUC_RT")
        private String flucRt;
        @JsonProperty("NAV")
        private String nav;
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
        @JsonProperty("INVSTASST_NETASST_TOTAMT")
        private String invstasstNetasstTotamt;
        @JsonProperty("LIST_SHRS")
        private String listShrs;
        @JsonProperty("IDX_IND_NM")
        private String idxIndNm;
        @JsonProperty("OBJ_STKPRC_IDX")
        private String objStkprcIdx;
        @JsonProperty("CMPPREVDD_IDX")
        private String cmpprevddIdx;
        @JsonProperty("FLUC_RT_IDX")
        private String flucRtIdx;

        public EtfTradingInfo toDomain() {
            return EtfTradingInfo.builder()
                    .basDd(basDd)
                    .isuCd(isuCd)
                    .isuNm(isuNm)
                    .tddClsprc(tddClsprc)
                    .cmpprevddPrc(cmpprevddPrc)
                    .flucRt(flucRt)
                    .nav(nav)
                    .tddOpnprc(tddOpnprc)
                    .tddHgprc(tddHgprc)
                    .tddLwprc(tddLwprc)
                    .accTrdvol(accTrdvol)
                    .accTrdval(accTrdval)
                    .mktcap(mktcap)
                    .invstasstNetasstTotamt(invstasstNetasstTotamt)
                    .listShrs(listShrs)
                    .idxIndNm(idxIndNm)
                    .objStkprcIdx(objStkprcIdx)
                    .cmpprevddIdx(cmpprevddIdx)
                    .flucRtIdx(flucRtIdx)
                    .build();
        }
    }

    public List<EtfTradingInfo> toDomainList() {
        if (outBlock1 == null)
            return List.of();
        return outBlock1.stream()
                .map(OutBlock::toDomain)
                .collect(Collectors.toList());
    }
}
