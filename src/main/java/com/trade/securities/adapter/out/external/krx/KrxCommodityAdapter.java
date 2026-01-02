package com.trade.securities.adapter.out.external.krx;

import com.trade.securities.adapter.out.external.krx.dto.CommodityTradingPriceResponse;
import com.trade.securities.application.port.out.LoadCommodityTradingInfoPort;
import com.trade.securities.domain.CommodityTradingInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class KrxCommodityAdapter implements LoadCommodityTradingInfoPort {

    private final WebClient webClient;

    @Value("${krx.api.key}")
    private String apiKey;

    private static final Map<String, String> ENDPOINTS = Map.of(
            "OIL", "https://data-dbg.krx.co.kr/svc/apis/gen/oil_bydd_trd",
            "GOLD", "https://data-dbg.krx.co.kr/svc/apis/gen/gold_bydd_trd",
            "ETS", "https://data-dbg.krx.co.kr/svc/apis/gen/ets_bydd_trd");

    @Override
    public List<CommodityTradingInfo> loadCommodityTradingInfo(String marketType, String basDd) {
        String url = ENDPOINTS.get(marketType.toUpperCase());
        if (url == null) {
            log.error("Invalid commodity market type: {}", marketType);
            return List.of();
        }

        log.info("Loading Commodity trading info from KRX for market: {}, date: {}", marketType, basDd);

        try {
            String rawResponse = webClient.post()
                    .uri(url)
                    .header("AUTH_KEY", apiKey)
                    .bodyValue(Objects.requireNonNull(Map.of("basDd", basDd)))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            CommodityTradingPriceResponse response = CommodityTradingPriceResponse.fromJson(rawResponse);

            return response != null ? response.toDomainList() : List.of();
        } catch (Exception e) {
            log.error("Error fetching Commodity data from KRX for market: {}, url: {}", marketType, url, e);
            return List.of();
        }
    }
}
