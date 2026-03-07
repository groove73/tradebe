package com.trade.securities.adapter.out.external.krx

import com.trade.securities.adapter.out.external.krx.dto.DerivativesTradingPriceResponse
import com.trade.securities.application.port.out.LoadDerivativesTradingInfoPort
import com.trade.securities.domain.DerivativesTradingInfo
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class KrxDerivativesAdapter(
    private val restClient: RestClient,
    @Value("\${krx.api.key}") private val apiKey: String
) : LoadDerivativesTradingInfoPort {

    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        private val ENDPOINTS = mapOf(
            "FUT_NORMAL" to "https://data-dbg.krx.co.kr/svc/apis/drv/fut_bydd_trd",
            "FUT_STK_KOSPI" to "https://data-dbg.krx.co.kr/svc/apis/drv/eqsfu_stk_bydd_trd",
            "FUT_STK_KOSDAQ" to "https://data-dbg.krx.co.kr/svc/apis/drv/eqkfu_ksq_bydd_trd",
            "OPT_NORMAL" to "https://data-dbg.krx.co.kr/svc/apis/drv/opt_bydd_trd",
            "OPT_STK_KOSPI" to "https://data-dbg.krx.co.kr/svc/apis/drv/eqsop_bydd_trd",
            "OPT_STK_KOSDAQ" to "https://data-dbg.krx.co.kr/svc/apis/drv/eqkop_bydd_trd"
        )
    }

    override fun loadDerivativesTradingInfo(marketType: String, basDd: String): List<DerivativesTradingInfo> {
        val url = ENDPOINTS[marketType.uppercase()]
        if (url == null) {
            log.error("Invalid derivatives market type: $marketType")
            return emptyList()
        }

        log.info("Loading Derivatives trading info from KRX for market: {}, date: {}", marketType, basDd)

        return try {
            val rawResponse = restClient.post()
                .uri(url)
                .header("AUTH_KEY", apiKey)
                .body(mapOf("basDd" to basDd))
                .retrieve()
                .body(String::class.java)

            val response = rawResponse?.let { DerivativesTradingPriceResponse.fromJson(it) }

            response?.toDomainList() ?: emptyList()
        } catch (e: Exception) {
            log.error("Error fetching Derivatives data from KRX for market type: $marketType, url: $url", e)
            emptyList()
        }
    }
}
