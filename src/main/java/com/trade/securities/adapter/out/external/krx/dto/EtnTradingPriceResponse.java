package com.trade.securities.adapter.out.external.krx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trade.securities.domain.EtnTradingInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class EtnTradingPriceResponse {

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
        @JsonProperty("PER1SECU_INDIC_VAL")
        private String per1secuIndicVal;
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
        @JsonProperty("INDIC_VAL_AMT")
        private String indicValAmt;
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

        public EtnTradingInfo toDomain() {
            return EtnTradingInfo.builder()
                    .basDd(basDd)
                    .isuCd(isuCd)
                    .isuNm(isuNm)
                    .tddClsprc(tddClsprc)
                    .cmpprevddPrc(cmpprevddPrc)
                    .flucRt(flucRt)
                    .per1secuIndicVal(per1secuIndicVal)
                    .tddOpnprc(tddOpnprc)
                    .tddHgprc(tddHgprc)
                    .tddLwprc(tddLwprc)
                    .accTrdvol(accTrdvol)
                    .accTrdval(accTrdval)
                    .mktcap(mktcap)
                    .indicValAmt(indicValAmt)
                    .listShrs(listShrs)
                    .idxIndNm(idxIndNm)
                    .objStkprcIdx(objStkprcIdx)
                    .cmpprevddIdx(cmpprevddIdx)
                    .flucRtIdx(flucRtIdx)
                    .build();
        }
    }

    public List<EtnTradingInfo> toDomainList() {
        if (outBlock1 == null)
            return List.of();
        return outBlock1.stream()
                .map(OutBlock::toDomain)
                .collect(Collectors.toList());
    }
}
