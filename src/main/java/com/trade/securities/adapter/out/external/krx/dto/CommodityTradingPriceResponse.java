package com.trade.securities.adapter.out.external.krx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trade.securities.domain.CommodityTradingInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Getter
@NoArgsConstructor
public class CommodityTradingPriceResponse {

    private List<OutBlock> outBlock1;

    public void setOutBlock1(List<OutBlock> outBlock1) {
        this.outBlock1 = outBlock1;
    }

    @JsonProperty("OutBlock_1")
    public void setOutBlock1FromProperty(List<OutBlock> outBlock1) {
        this.outBlock1 = outBlock1;
    }

    @Getter
    @NoArgsConstructor
    public static class OutBlock {
        @JsonProperty("BAS_DD")
        private String basDd;

        // Gold & Emissions
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
        @JsonProperty("TDD_OPNPRC")
        private String tddOpnprc;
        @JsonProperty("TDD_HGPRC")
        private String tddHgprc;
        @JsonProperty("TDD_LWPRC")
        private String tddLwprc;

        // Oil
        @JsonProperty("OIL_NM")
        private String oilNm;
        @JsonProperty("WT_AVG_PRC")
        private String wtAvgPrc;
        @JsonProperty("WT_DIS_AVG_PRC")
        private String wtDisAvgPrc;

        // Joint
        @JsonProperty("ACC_TRDVOL")
        private String accTrdvol;
        @JsonProperty("ACC_TRDVAL")
        private String accTrdval;

        public CommodityTradingInfo toDomain() {
            return CommodityTradingInfo.builder()
                    .basDd(basDd)
                    .isuCd(isuCd)
                    .isuNm(isuNm)
                    .tddClsprc(tddClsprc)
                    .cmpprevddPrc(cmpprevddPrc)
                    .flucRt(flucRt)
                    .tddOpnprc(tddOpnprc)
                    .tddHgprc(tddHgprc)
                    .tddLwprc(tddLwprc)
                    .oilNm(oilNm)
                    .wtAvgPrc(wtAvgPrc)
                    .wtDisAvgPrc(wtDisAvgPrc)
                    .accTrdvol(accTrdvol)
                    .accTrdval(accTrdval)
                    .build();
        }
    }

    public List<CommodityTradingInfo> toDomainList() {
        if (outBlock1 == null)
            return List.of();
        return outBlock1.stream()
                .map(OutBlock::toDomain)
                .collect(Collectors.toList());
    }

    public static CommodityTradingPriceResponse fromJson(String json) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            JsonNode node = mapper.readTree(json);
            CommodityTradingPriceResponse response = new CommodityTradingPriceResponse();

            if (node.isArray()) {
                List<OutBlock> list = mapper.convertValue(node, new TypeReference<List<OutBlock>>() {
                });
                response.setOutBlock1(list);
            } else if (node.has("OutBlock_1")) {
                List<OutBlock> list = mapper.convertValue(node.get("OutBlock_1"), new TypeReference<List<OutBlock>>() {
                });
                response.setOutBlock1(list);
            } else {
                log.warn("Unknown response format: {}", json);
            }
            return response;
        } catch (Exception e) {
            log.error("Failed to parse JSON: {}", json, e);
            return null;
        }
    }
}
