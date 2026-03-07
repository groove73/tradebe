package com.trade.securities.adapter.out.external.krx

import com.trade.securities.adapter.out.external.krx.dto.CommodityTradingPriceResponse
import com.trade.securities.application.port.out.LoadCommodityTradingInfoPort
import com.trade.securities.domain.CommodityTradingInfo
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class KrxCommodityAdapter(
    private val restClient: RestClient,
    @Value("\${krx.api.key}") private val apiKey: String
) : LoadCommodityTradingInfoPort {

    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        private val ENDPOINTS = mapOf(
            "OIL" to "https://data-dbg.krx.co.kr/svc/apis/gen/oil_bydd_trd",
            "GOLD" to "https://data-dbg.krx.co.kr/svc/apis/gen/gold_bydd_trd",
            "ETS" to "https://data-dbg.krx.co.kr/svc/apis/gen/ets_bydd_trd"
        )
    }

    override fun loadCommodityTradingInfo(marketType: String, basDd: String): List<CommodityTradingInfo> {
        val url = ENDPOINTS[marketType.uppercase()]
        if (url == null) {
            log.error("Invalid commodity market type: $marketType")
            return emptyList()
        }

        log.info("Loading Commodity trading info from KRX for market: {}, date: {}", marketType, basDd)

        return try {
            val rawResponse = restClient.post()
                .uri(url)
                .header("AUTH_KEY", apiKey)
                .body(mapOf("basDd" to basDd))
                .retrieve()
                .body(String::class.java)

            val response = rawResponse?.let { CommodityTradingPriceResponse.fromJson(it) }

            response?.toDomainList() ?: emptyList()
        } catch (e: Exception) {
            log.error("Error fetching Commodity data from KRX for market: $marketType, url: $url", e)
            emptyList()
        }
    }
}
