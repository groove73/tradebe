package com.trade.securities.adapter.out.external.krx;

import com.trade.securities.adapter.out.external.krx.dto.EtfTradingPriceResponse;
import com.trade.securities.application.port.out.LoadEtfTradingInfoPort;
import com.trade.securities.domain.EtfTradingInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class KrxEtfAdapter implements LoadEtfTradingInfoPort {

    private final WebClient webClient;

    @Value("${krx.api.key}")
    private String apiKey;

    private static final String KRX_API_URL = "https://data-dbg.krx.co.kr/svc/apis/etp/etf_bydd_trd";

    @Override
    public List<EtfTradingInfo> loadEtfTradingInfo(String basDd) {
        log.info("Loading ETF trading info from KRX for date: {}", basDd);

        try {
            EtfTradingPriceResponse response = webClient.post()
                    .uri(KRX_API_URL)
                    .header("AUTH_KEY", apiKey)
                    .bodyValue(java.util.Objects.requireNonNull(java.util.Map.of("basDd", basDd)))
                    .retrieve()
                    .bodyToMono(EtfTradingPriceResponse.class)
                    .block();

            return response != null ? response.toDomainList() : List.of();
        } catch (Exception e) {
            log.error("Error fetching ETF data from KRX", e);
            return List.of();
        }
    }
}
