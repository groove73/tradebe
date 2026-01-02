package com.trade.securities.adapter.out.external.krx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trade.securities.domain.DerivativesTradingInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Getter
@NoArgsConstructor
public class DerivativesTradingPriceResponse {

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
        @JsonProperty("PROD_NM")
        private String prodNm;
        @JsonProperty("MKT_NM")
        private String mktNm;
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
        @JsonProperty("ACC_OPNINT_QTY")
        private String accOpnintQty;

        // 선물 전용
        @JsonProperty("SPOT_PRC")
        private String spotPrc;
        @JsonProperty("SETL_PRC")
        private String setlPrc;

        // 옵션 전용
        @JsonProperty("RGHT_TP_NM")
        private String rghtTpNm;
        @JsonProperty("IMP_VOLT")
        private String impVolt;
        @JsonProperty("NXTD_BAS_PRC")
        private String nxtdBasPrc;

        public DerivativesTradingInfo toDomain() {
            return DerivativesTradingInfo.builder()
                    .basDd(basDd)
                    .prodNm(prodNm)
                    .mktNm(mktNm)
                    .isuCd(isuCd)
                    .isuNm(isuNm)
                    .tddClsprc(tddClsprc)
                    .cmpprevddPrc(cmpprevddPrc)
                    .tddOpnprc(tddOpnprc)
                    .tddHgprc(tddHgprc)
                    .tddLwprc(tddLwprc)
                    .accTrdvol(accTrdvol)
                    .accTrdval(accTrdval)
                    .accOpnintQty(accOpnintQty)
                    .spotPrc(spotPrc)
                    .setlPrc(setlPrc)
                    .rghtTpNm(rghtTpNm)
                    .impVolt(impVolt)
                    .nxtdBasPrc(nxtdBasPrc)
                    .build();
        }
    }

    public List<DerivativesTradingInfo> toDomainList() {
        if (outBlock1 == null)
            return List.of();
        return outBlock1.stream()
                .map(OutBlock::toDomain)
                .collect(Collectors.toList());
    }

    public static DerivativesTradingPriceResponse fromJson(String json) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            JsonNode node = mapper.readTree(json);
            DerivativesTradingPriceResponse response = new DerivativesTradingPriceResponse();

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
