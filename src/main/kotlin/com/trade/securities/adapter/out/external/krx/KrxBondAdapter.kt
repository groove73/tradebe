package com.trade.securities.adapter.out.external.krx

import com.trade.securities.adapter.out.external.krx.dto.BondTradingPriceResponse
import com.trade.securities.application.port.out.LoadBondTradingInfoPort
import com.trade.securities.domain.BondTradingInfo
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class KrxBondAdapter(
    private val restClient: RestClient,
    @Value("\${krx.api.key}") private val apiKey: String
) : LoadBondTradingInfoPort {

    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        private val ENDPOINTS = mapOf(
            "TREASURY" to "https://data-dbg.krx.co.kr/svc/apis/bon/kts_bydd_trd",
            "GENERAL" to "https://data-dbg.krx.co.kr/svc/apis/bon/bnd_bydd_trd",
            "SMALL" to "https://data-dbg.krx.co.kr/svc/apis/bon/smb_bydd_trd"
        )
    }

    override fun loadBondTradingInfo(marketType: String, basDd: String): List<BondTradingInfo> {
        val url = ENDPOINTS[marketType.uppercase()]
        if (url == null) {
            log.error("Invalid market type: $marketType")
            return emptyList()
        }

        log.info("Loading Bond trading info from KRX for market: {}, date: {}", marketType, basDd)

        return try {
            val response = restClient.post()
                .uri(url)
                .header("AUTH_KEY", apiKey)
                .body(mapOf("basDd" to basDd))
                .retrieve()
                .body(BondTradingPriceResponse::class.java)

            response?.toDomainList() ?: emptyList()
        } catch (e: Exception) {
            log.error("Error fetching Bond data from KRX for market: $marketType", e)
            emptyList()
        }
    }
}
