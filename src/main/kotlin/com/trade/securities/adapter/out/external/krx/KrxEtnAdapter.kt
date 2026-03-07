package com.trade.securities.adapter.out.external.krx

import com.trade.securities.adapter.out.external.krx.dto.EtnTradingPriceResponse
import com.trade.securities.application.port.out.LoadEtnTradingInfoPort
import com.trade.securities.domain.EtnTradingInfo
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class KrxEtnAdapter(
    private val restClient: RestClient,
    @Value("\${krx.api.key}") private val apiKey: String
) : LoadEtnTradingInfoPort {

    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        private const val KRX_API_URL = "https://data-dbg.krx.co.kr/svc/apis/etp/etn_bydd_trd"
    }

    override fun loadEtnTradingInfo(basDd: String): List<EtnTradingInfo> {
        log.info("Loading ETN trading info from KRX for date: {}", basDd)

        return try {
            val response = restClient.post()
                .uri(KRX_API_URL)
                .header("AUTH_KEY", apiKey)
                .body(mapOf("basDd" to basDd))
                .retrieve()
                .body(EtnTradingPriceResponse::class.java)

            response?.toDomainList() ?: emptyList()
        } catch (e: Exception) {
            log.error("Error fetching ETN data from KRX", e)
            emptyList()
        }
    }
}
