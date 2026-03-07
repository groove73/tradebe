package com.trade.securities.adapter.out.external.krx

import com.trade.securities.adapter.out.external.krx.dto.EtfTradingPriceResponse
import com.trade.securities.application.port.out.LoadEtfTradingInfoPort
import com.trade.securities.domain.EtfTradingInfo
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class KrxEtfAdapter(
    private val restClient: RestClient,
    @Value("\${krx.api.key}") private val apiKey: String
) : LoadEtfTradingInfoPort {

    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        private const val KRX_API_URL = "https://data-dbg.krx.co.kr/svc/apis/etp/etf_bydd_trd"
    }

    override fun loadEtfTradingInfo(basDd: String): List<EtfTradingInfo> {
        log.info("Loading ETF trading info from KRX for date: {}", basDd)

        return try {
            val response = restClient.post()
                .uri(KRX_API_URL)
                .header("AUTH_KEY", apiKey)
                .body(mapOf("basDd" to basDd))
                .retrieve()
                .body(EtfTradingPriceResponse::class.java)

            response?.toDomainList() ?: emptyList()
        } catch (e: Exception) {
            log.error("Error fetching ETF data from KRX", e)
            emptyList()
        }
    }
}
