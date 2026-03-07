package com.trade.securities.adapter.out.external.krx

import com.trade.securities.adapter.out.external.krx.dto.ElwTradingPriceResponse
import com.trade.securities.application.port.out.LoadElwTradingInfoPort
import com.trade.securities.domain.ElwTradingInfo
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class KrxElwAdapter(
    private val restClient: RestClient,
    @Value("\${krx.api.key}") private val apiKey: String
) : LoadElwTradingInfoPort {

    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        private const val KRX_API_URL = "https://data-dbg.krx.co.kr/svc/apis/etp/elw_bydd_trd"
    }

    override fun loadElwTradingInfo(basDd: String): List<ElwTradingInfo> {
        log.info("Loading ELW trading info from KRX for date: {}", basDd)

        return try {
            val response = restClient.post()
                .uri(KRX_API_URL)
                .header("AUTH_KEY", apiKey)
                .body(mapOf("basDd" to basDd))
                .retrieve()
                .body(ElwTradingPriceResponse::class.java)

            response?.toDomainList() ?: emptyList()
        } catch (e: Exception) {
            log.error("Error fetching ELW data from KRX", e)
            emptyList()
        }
    }
}
