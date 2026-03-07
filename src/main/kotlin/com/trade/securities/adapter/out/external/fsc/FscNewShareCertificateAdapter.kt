package com.trade.securities.adapter.out.external.fsc

import com.trade.securities.application.port.out.LoadFscNewShareCertificatePort
import com.trade.securities.domain.FscNewShareCertificate
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import org.springframework.web.util.UriComponentsBuilder

@Component
class FscNewShareCertificateAdapter(
    private val restClient: RestClient,
    @Value("\${fsc.api.key}") private val apiKey: String,
    @Value("\${fsc.api.urls.new-share:https://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/getPreemptiveRightCertificatePriceInfo}") private val apiUrl: String
) : LoadFscNewShareCertificatePort {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun loadNewShareCertificates(
        pageNo: Int,
        numOfRows: Int,
        basDt: String,
        itmsNm: String?,
        likeItmsNm: String?
    ): Map<String, Any?> {
        return try {
            val builder = UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("serviceKey", apiKey)
                .queryParam("numOfRows", numOfRows)
                .queryParam("pageNo", pageNo)
                .queryParam("resultType", "json")
                .queryParam("basDt", basDt)

            itmsNm?.takeIf { it.isNotEmpty() }?.let { builder.queryParam("itmsNm", it) }
            likeItmsNm?.takeIf { it.isNotEmpty() }?.let { builder.queryParam("likeItmsNm", it) }

            val url = builder.build().toUriString()

            val response = restClient.get()
                .uri(url)
                .retrieve()
                .body(NewShareResponse::class.java)

            response?.response?.body?.let { body ->
                val items = body.items?.item?.map { mapToDomain(it) } ?: emptyList()
                mapOf(
                    "items" to items,
                    "totalCount" to body.totalCount,
                    "pageNo" to body.pageNo,
                    "numOfRows" to body.numOfRows
                )
            } ?: run {
                log.warn("FSC API (New Share) returned empty or null for basDt: {}", basDt)
                mapOf("items" to emptyList<FscNewShareCertificate>(), "totalCount" to 0)
            }
        } catch (e: Exception) {
            log.error("Failed to fetch new share certificates from FSC", e)
            mapOf("items" to emptyList<FscNewShareCertificate>(), "totalCount" to 0)
        }
    }

    private fun mapToDomain(item: FscNewShareItem): FscNewShareCertificate {
        return FscNewShareCertificate(
            basDt = item.basDt,
            srtCd = item.srtnCd,
            isinCd = item.isinCd,
            itmsNm = item.itmsNm,
            mrktCtg = item.mrktCtg,
            clpr = item.clpr,
            vs = item.vs,
            fltRt = item.fltRt,
            mkp = item.mkp,
            hipr = item.hipr,
            lopr = item.lopr,
            trqu = item.trqu,
            trPrc = item.trPrc,
            lstDt = item.lstDt,
            delstDt = item.delstDt,
            stckSrtnCd = item.stckSrtnCd,
            stckItmsNm = item.stckItmsNm,
            stckClpr = item.stckClpr
        )
    }

    data class NewShareResponse(val response: com.trade.securities.adapter.out.external.fsc.dto.ResponseContent<FscNewShareItem>? = null)

    data class FscNewShareItem(
        val basDt: String? = null,
        val srtnCd: String? = null,
        val isinCd: String? = null,
        val itmsNm: String? = null,
        val mrktCtg: String? = null,
        val clpr: String? = null,
        val vs: String? = null,
        val fltRt: String? = null,
        val mkp: String? = null,
        val hipr: String? = null,
        val lopr: String? = null,
        val trqu: String? = null,
        val trPrc: String? = null,
        val lstDt: String? = null,
        val delstDt: String? = null,
        val stckSrtnCd: String? = null,
        val stckItmsNm: String? = null,
        val stckClpr: String? = null
    )
}
