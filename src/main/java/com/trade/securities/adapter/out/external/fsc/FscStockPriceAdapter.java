package com.trade.securities.adapter.out.external.fsc;

import com.trade.securities.application.port.out.LoadFscStockPricePort;
import com.trade.securities.domain.FscStockPrice;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class FscStockPriceAdapter implements LoadFscStockPricePort {

    private final WebClient webClient;

    @Value("${fsc.api.key}")
    private String apiKey;

    @Value("${fsc.api.url}")
    private String apiUrl;

    @Override
    public Map<String, Object> loadStockPrices(int pageNo, int numOfRows, String basDt, String itmsNm,
            String likeItmsNm) {
        try {
            org.springframework.web.util.UriComponentsBuilder builder = org.springframework.web.util.UriComponentsBuilder
                    .fromUriString(apiUrl)
                    .queryParam("serviceKey", apiKey)
                    .queryParam("numOfRows", numOfRows)
                    .queryParam("pageNo", pageNo)
                    .queryParam("resultType", "json")
                    .queryParam("basDt", basDt);

            if (itmsNm != null && !itmsNm.isEmpty()) {
                builder.queryParam("itmsNm", itmsNm);
            }
            if (likeItmsNm != null && !likeItmsNm.isEmpty()) {
                builder.queryParam("likeItmsNm", likeItmsNm);
            }

            String url = builder.build().toUriString();

            FscResponse response = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(FscResponse.class)
                    .block();

            if (response != null && response.getResponse() != null && response.getResponse().getBody() != null) {
                FscBody body = response.getResponse().getBody();
                List<FscStockPrice> items = body.getItems().getItem().stream()
                        .map(this::mapToDomain)
                        .collect(Collectors.toList());

                return Map.of(
                        "items", items,
                        "totalCount", body.getTotalCount(),
                        "pageNo", body.getPageNo(),
                        "numOfRows", body.getNumOfRows());
            }
            log.warn("FSC API returned empty or null for basDt: {}", basDt);
            return Map.of("items", Collections.emptyList(), "totalCount", 0);

        } catch (Exception e) {
            log.error("Failed to fetch stock prices from FSC at " + apiUrl, e);
            return Map.of("items", Collections.emptyList(), "totalCount", 0);
        }
    }

    private FscStockPrice mapToDomain(FscItem item) {
        return FscStockPrice.builder()
                .basDt(item.getBasDt())
                .srtCd(item.getSrtnCd())
                .isinCd(item.getIsinCd())
                .itmsNm(item.getItmsNm())
                .mrktCtg(item.getMrktCtg())
                .clpr(item.getClpr())
                .vs(item.getVs())
                .fltRt(item.getFltRt())
                .mkp(item.getMkp())
                .hipr(item.getHipr())
                .lopr(item.getLopr())
                .trqu(item.getTrqu())
                .trPrc(item.getTrPrc())
                .lstgStCnt(item.getLstgStCnt())
                .mrktTotAmt(item.getMrktTotAmt())
                .build();
    }

    @Data
    static class FscResponse {
        private ResponseContent response;
    }

    @Data
    static class ResponseContent {
        private FscHeader header;
        private FscBody body;
    }

    @Data
    static class FscHeader {
        private String resultCode;
        private String resultMsg;
    }

    @Data
    static class FscBody {
        private int numOfRows;
        private int pageNo;
        private int totalCount;
        private FscItems items;
    }

    @Data
    static class FscItems {
        private List<FscItem> item;
    }

    @Data
    static class FscItem {
        private String basDt;
        private String srtnCd;
        private String isinCd;
        private String itmsNm;
        private String mrktCtg;
        private String clpr;
        private String vs;
        private String fltRt;
        private String mkp;
        private String hipr;
        private String lopr;
        private String trqu;
        private String trPrc;
        private String lstgStCnt;
        private String mrktTotAmt;
    }
}
