package com.trade.securities.adapter.out.external.krx;

import com.trade.securities.adapter.out.external.krx.dto.BondTradingPriceResponse;
import com.trade.securities.application.port.out.LoadBondTradingInfoPort;
import com.trade.securities.domain.BondTradingInfo;
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
public class KrxBondAdapter implements LoadBondTradingInfoPort {

    private final WebClient webClient;

    @Value("${krx.api.key}")
    private String apiKey;

    private static final Map<String, String> ENDPOINTS = Map.of(
            "TREASURY", "https://data-dbg.krx.co.kr/svc/apis/bon/kts_bydd_trd",
            "GENERAL", "https://data-dbg.krx.co.kr/svc/apis/bon/bnd_bydd_trd",
            "SMALL", "https://data-dbg.krx.co.kr/svc/apis/bon/smb_bydd_trd");

    @Override
    public List<BondTradingInfo> loadBondTradingInfo(String marketType, String basDd) {
        String url = ENDPOINTS.get(marketType.toUpperCase());
        if (url == null) {
            log.error("Invalid market type: {}", marketType);
            return List.of();
        }

        log.info("Loading Bond trading info from KRX for market: {}, date: {}", marketType, basDd);

        try {
            BondTradingPriceResponse response = webClient.post()
                    .uri(url)
                    .header("AUTH_KEY", apiKey)
                    .bodyValue(java.util.Objects.requireNonNull(Map.of("basDd", basDd)))
                    .retrieve()
                    .bodyToMono(BondTradingPriceResponse.class)
                    .block();

            return response != null ? response.toDomainList() : List.of();
        } catch (Exception e) {
            log.error("Error fetching Bond data from KRX for market: {}", marketType, e);
            return List.of();
        }
    }
}
