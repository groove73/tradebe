package com.trade.securities.adapter.out.external.krx;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trade.securities.application.port.out.LoadMarketDataPort;
import com.trade.securities.domain.MarketData;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.trade.securities.domain.StockData;

@Slf4j
@Component
@RequiredArgsConstructor
public class KrxAdapter implements LoadMarketDataPort {

    private final WebClient webClient;

    @Value("${krx.api.key}")
    private String apiKey;

    @Value("${krx.api.urls.kospi}")
    private String kospiUrl;

    @Value("${krx.api.urls.krx}")
    private String krxUrl;

    @Value("${krx.api.urls.stock}")
    private String stockUrl;

    @Value("${krx.api.urls.konex}")
    private String konexUrl;

    @Value("${krx.api.urls.stk}")
    private String stkUrl;

    @Override
    public List<MarketData> loadMarketData(String date, String type) {
        String url = "KRX".equalsIgnoreCase(type) ? krxUrl : kospiUrl;
        try {
            KrxResponse response = webClient.post()
                    .uri(url)
                    .header("AUTH_KEY", apiKey)
                    .bodyValue(java.util.Map.of("basDd", date))
                    .retrieve()
                    .bodyToMono(KrxResponse.class)
                    .block();

            if (response != null && response.getOutBlock1() != null) {
                return response.getOutBlock1().stream()
                        .filter(item -> !"-".equals(item.getClsprcIdx())) // Filter out placeholders
                        .map(this::mapToDomain)
                        .collect(Collectors.toList());
            }
            log.warn("Krx API returned empty or null OutBlock_1 for type: " + type + ", date: " + date);
            return Collections.emptyList();

        } catch (Exception e) {
            log.error("Failed to fetch data from KRX at " + url, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<StockData> loadStockData(String date, String type) {
        String url = stockUrl;
        if ("KONEX".equalsIgnoreCase(type)) {
            url = konexUrl;
        } else if ("KOSPI".equalsIgnoreCase(type)) {
            url = stkUrl;
        }
        try {
            KrxStockResponse response = webClient.post()
                    .uri(url)
                    .header("AUTH_KEY", apiKey)
                    .bodyValue(java.util.Map.of("basDd", date))
                    .retrieve()
                    .bodyToMono(KrxStockResponse.class)
                    .block();

            if (response != null && response.getOutBlock1() != null) {
                return response.getOutBlock1().stream()
                        .map(this::mapToStockDomain)
                        .collect(Collectors.toList());
            }
            log.warn("Krx API returned empty or null for stock data, type: " + type + ", date: " + date);
            return Collections.emptyList();
        } catch (Exception e) {
            log.error("Failed to fetch stock data from KRX at " + url, e);
            return Collections.emptyList();
        }
    }

    private MarketData mapToDomain(KrxItem item) {
        return MarketData.builder()
                .basDt(item.getBasDd())
                .idxNm(item.getIdxNm())
                .clpr(item.getClsprcIdx())
                .fltRt(item.getFlucRt())
                .build();
    }

    private StockData mapToStockDomain(KrxStockItem item) {
        return StockData.builder()
                .basDd(item.getBasDd())
                .isuCd(item.getIsuCd())
                .isuNm(item.getIsuNm())
                .mktNm(item.getMktNm())
                .sectTpNm(item.getSectTpNm())
                .tddClsprc(item.getTddClsprc())
                .cmpprevddPrc(item.getCmpprevddPrc())
                .flucRt(item.getFlucRt())
                .tddOpnprc(item.getTddOpnprc())
                .tddHgprc(item.getTddHgprc())
                .tddLwprc(item.getTddLwprc())
                .accTrdvol(item.getAccTrdvol())
                .accTrdval(item.getAccTrdval())
                .mktcap(item.getMktcap())
                .listShrs(item.getListShrs())
                .build();
    }

    @Data
    static class KrxResponse {
        @JsonProperty("OutBlock_1")
        private List<KrxItem> outBlock1;
    }

    @Data
    static class KrxItem {
        @JsonProperty("BAS_DD")
        private String basDd;
        @JsonProperty("IDX_NM")
        private String idxNm;
        @JsonProperty("CLSPRC_IDX")
        private String clsprcIdx;
        @JsonProperty("FLUC_RT")
        private String flucRt;
    }

    @Data
    static class KrxStockResponse {
        @JsonProperty("OutBlock_1")
        private List<KrxStockItem> outBlock1;
    }

    @Data
    static class KrxStockItem {
        @JsonProperty("BAS_DD")
        private String basDd;
        @JsonProperty("ISU_CD")
        private String isuCd;
        @JsonProperty("ISU_NM")
        private String isuNm;
        @JsonProperty("MKT_NM")
        private String mktNm;
        @JsonProperty("SECT_TP_NM")
        private String sectTpNm;
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
        @JsonProperty("ACC_TRDVOL")
        private String accTrdvol;
        @JsonProperty("ACC_TRDVAL")
        private String accTrdval;
        @JsonProperty("MKTCAP")
        private String mktcap;
        @JsonProperty("LIST_SHRS")
        private String listShrs;
    }
}
