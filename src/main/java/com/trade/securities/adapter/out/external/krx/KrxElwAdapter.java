package com.trade.securities.adapter.out.external.krx;

import com.trade.securities.adapter.out.external.krx.dto.ElwTradingPriceResponse;
import com.trade.securities.application.port.out.LoadElwTradingInfoPort;
import com.trade.securities.domain.ElwTradingInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class KrxElwAdapter implements LoadElwTradingInfoPort {

    private final RestClient restClient;

    @Value("${krx.api.key}")
    private String apiKey;

    private static final String KRX_API_URL = "https://data-dbg.krx.co.kr/svc/apis/etp/elw_bydd_trd";

    @Override
    public List<ElwTradingInfo> loadElwTradingInfo(String basDd) {
        log.info("Loading ELW trading info from KRX for date: {}", basDd);

        try {
            ElwTradingPriceResponse response = restClient.post()
                    .uri(KRX_API_URL)
                    .header("AUTH_KEY", apiKey)
                    .body(java.util.Objects.requireNonNull(java.util.Map.of("basDd", basDd)))
                    .retrieve()
                    .body(ElwTradingPriceResponse.class);

            return response != null ? response.toDomainList() : List.of();
        } catch (Exception e) {
            log.error("Error fetching ELW data from KRX", e);
            return List.of();
        }
    }
}
