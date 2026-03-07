package com.trade.securities.adapter.out.external.krx

import com.fasterxml.jackson.annotation.JsonProperty
import com.trade.securities.application.port.out.LoadMarketDataPort
import com.trade.securities.domain.MarketData
import com.trade.securities.domain.StockData
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class KrxAdapter(
    private val restClient: RestClient,
    @Value("\${krx.api.key}") private val apiKey: String,
    @Value("\${krx.api.urls.kospi}") private val kospiUrl: String,
    @Value("\${krx.api.urls.krx}") private val krxUrl: String,
    @Value("\${krx.api.urls.stock}") private val stockUrl: String,
    @Value("\${krx.api.urls.konex}") private val konexUrl: String,
    @Value("\${krx.api.urls.stk}") private val stkUrl: String
) : LoadMarketDataPort {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun loadMarketData(date: String, type: String): List<MarketData> {
        val url = if ("KRX".equals(type, ignoreCase = true)) krxUrl else kospiUrl
        return try {
            val response = restClient.post()
                .uri(url)
                .header("AUTH_KEY", apiKey)
                .body(mapOf("basDd" to date))
                .retrieve()
                .body(KrxResponse::class.java)

            response?.outBlock1?.filter { it.clsprcIdx != "-" }?.map { mapToDomain(it) } ?: emptyList()
        } catch (e: Exception) {
            log.error("Failed to fetch data from KRX at $url", e)
            emptyList()
        }
    }

    override fun loadStockData(date: String, type: String): List<StockData> {
        var url = stockUrl
        if ("KONEX".equals(type, ignoreCase = true)) {
            url = konexUrl
        } else if ("KOSPI".equals(type, ignoreCase = true)) {
            url = stkUrl
        }
        return try {
            val response = restClient.post()
                .uri(url)
                .header("AUTH_KEY", apiKey)
                .body(mapOf("basDd" to date))
                .retrieve()
                .body(KrxStockResponse::class.java)

            response?.outBlock1?.map { mapToStockDomain(it) } ?: emptyList()
        } catch (e: Exception) {
            log.error("Failed to fetch stock data from KRX at $url", e)
            emptyList()
        }
    }

    private fun mapToDomain(item: KrxItem): MarketData {
        return MarketData(
            basDt = item.basDd,
            idxNm = item.idxNm,
            clpr = item.clsprcIdx,
            fltRt = item.flucRt
        )
    }

    private fun mapToStockDomain(item: KrxStockItem): StockData {
        return StockData(
            basDd = item.basDd,
            isuCd = item.isuCd,
            isuNm = item.isuNm,
            mktNm = item.mktNm,
            sectTpNm = item.sectTpNm,
            tddClsprc = item.tddClsprc,
            cmpprevddPrc = item.cmpprevddPrc,
            flucRt = item.flucRt,
            tddOpnprc = item.tddOpnprc,
            tddHgprc = item.tddHgprc,
            tddLwprc = item.tddLwprc,
            accTrdvol = item.accTrdvol,
            accTrdval = item.accTrdval,
            mktcap = item.mktcap,
            listShrs = item.listShrs
        )
    }

    data class KrxResponse(
        @JsonProperty("OutBlock_1")
        val outBlock1: List<KrxItem>? = null
    )

    data class KrxItem(
        @JsonProperty("BAS_DD")
        val basDd: String? = null,
        @JsonProperty("IDX_NM")
        val idxNm: String? = null,
        @JsonProperty("CLSPRC_IDX")
        val clsprcIdx: String? = null,
        @JsonProperty("FLUC_RT")
        val flucRt: String? = null
    )

    data class KrxStockResponse(
        @JsonProperty("OutBlock_1")
        val outBlock1: List<KrxStockItem>? = null
    )

    data class KrxStockItem(
        @JsonProperty("BAS_DD")
        val basDd: String? = null,
        @JsonProperty("ISU_CD")
        val isuCd: String? = null,
        @JsonProperty("ISU_NM")
        val isuNm: String? = null,
        @JsonProperty("MKT_NM")
        val mktNm: String? = null,
        @JsonProperty("SECT_TP_NM")
        val sectTpNm: String? = null,
        @JsonProperty("TDD_CLSPRC")
        val tddClsprc: String? = null,
        @JsonProperty("CMPPREVDD_PRC")
        val cmpprevddPrc: String? = null,
        @JsonProperty("FLUC_RT")
        val flucRt: String? = null,
        @JsonProperty("TDD_OPNPRC")
        val tddOpnprc: String? = null,
        @JsonProperty("TDD_HGPRC")
        val tddHgprc: String? = null,
        @JsonProperty("TDD_LWPRC")
        val tddLwprc: String? = null,
        @JsonProperty("ACC_TRDVOL")
        val accTrdvol: String? = null,
        @JsonProperty("ACC_TRDVAL")
        val accTrdval: String? = null,
        @JsonProperty("MKTCAP")
        val mktcap: String? = null,
        @JsonProperty("LIST_SHRS")
        val listShrs: String? = null
    )
}
