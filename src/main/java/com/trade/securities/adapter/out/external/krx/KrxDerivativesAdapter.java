package com.trade.securities.adapter.out.external.krx;

import com.trade.securities.adapter.out.external.krx.dto.DerivativesTradingPriceResponse;
import com.trade.securities.application.port.out.LoadDerivativesTradingInfoPort;
import com.trade.securities.domain.DerivativesTradingInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class KrxDerivativesAdapter implements LoadDerivativesTradingInfoPort {

    private final WebClient webClient;

    @Value("${krx.api.key}")
    private String apiKey;

    private static final Map<String, String> ENDPOINTS = Map.of(
            "FUT_NORMAL", "https://data-dbg.krx.co.kr/svc/apis/drv/fut_bydd_trd",
            "FUT_STK_KOSPI", "https://data-dbg.krx.co.kr/svc/apis/drv/eqsfu_stk_bydd_trd",
            "FUT_STK_KOSDAQ", "https://data-dbg.krx.co.kr/svc/apis/drv/eqkfu_ksq_bydd_trd",
            "OPT_NORMAL", "https://data-dbg.krx.co.kr/svc/apis/drv/opt_bydd_trd",
            "OPT_STK_KOSPI", "https://data-dbg.krx.co.kr/svc/apis/drv/eqsop_bydd_trd",
            "OPT_STK_KOSDAQ", "https://data-dbg.krx.co.kr/svc/apis/drv/eqkop_bydd_trd");

    @Override
    public List<DerivativesTradingInfo> loadDerivativesTradingInfo(String marketType, String basDd) {
        String url = ENDPOINTS.get(marketType.toUpperCase());
        if (url == null) {
            log.error("Invalid derivatives market type: {}", marketType);
            return List.of();
        }

        log.info("Loading Derivatives trading info from KRX for market: {}, date: {}", marketType, basDd);

        try {
            String rawResponse = webClient.post()
                    .uri(url)
                    .header("AUTH_KEY", apiKey)
                    .bodyValue(java.util.Objects.requireNonNull(Map.of("basDd", basDd)))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            DerivativesTradingPriceResponse response = DerivativesTradingPriceResponse.fromJson(rawResponse);

            return response != null ? response.toDomainList() : List.of();
        } catch (Exception e) {
            log.error("Error fetching Derivatives data from KRX for market type: {}, url: {}", marketType, url, e);
            return List.of();
        }
    }
}
