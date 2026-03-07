package com.trade.securities.adapter.out.external.krx.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.trade.securities.domain.CommodityTradingInfo
import org.slf4j.LoggerFactory

data class CommodityTradingPriceResponse(
    var outBlock1: List<OutBlock>? = null
) {
    @JsonProperty("OutBlock_1")
    fun setOutBlock1FromProperty(outBlock1: List<OutBlock>?) {
        this.outBlock1 = outBlock1
    }

    data class OutBlock(
        @JsonProperty("BAS_DD") val basDd: String? = null,
        @JsonProperty("ISU_CD") val isuCd: String? = null,
        @JsonProperty("ISU_NM") val isuNm: String? = null,
        @JsonProperty("TDD_CLSPRC") val tddClsprc: String? = null,
        @JsonProperty("CMPPREVDD_PRC") val cmpprevddPrc: String? = null,
        @JsonProperty("FLUC_RT") val flucRt: String? = null,
        @JsonProperty("TDD_OPNPRC") val tddOpnprc: String? = null,
        @JsonProperty("TDD_HGPRC") val hgprc: String? = null,
        @JsonProperty("TDD_LWPRC") val lwprc: String? = null,
        @JsonProperty("OIL_NM") val oilNm: String? = null,
        @JsonProperty("WT_AVG_PRC") val wtAvgPrc: String? = null,
        @JsonProperty("WT_DIS_AVG_PRC") val wtDisAvgPrc: String? = null,
        @JsonProperty("ACC_TRDVOL") val accTrdvol: String? = null,
        @JsonProperty("ACC_TRDVAL") val accTrdval: String? = null
    ) {
        fun toDomain(): CommodityTradingInfo = CommodityTradingInfo(
            basDd = basDd,
            isuCd = isuCd,
            isuNm = isuNm,
            tddClsprc = tddClsprc,
            cmpprevddPrc = cmpprevddPrc,
            flucRt = flucRt,
            tddOpnprc = tddOpnprc,
            tddHgprc = hgprc,
            tddLwprc = lwprc,
            oilNm = oilNm,
            wtAvgPrc = wtAvgPrc,
            wtDisAvgPrc = wtDisAvgPrc,
            accTrdvol = accTrdvol,
            accTrdval = accTrdval
        )
    }

    fun toDomainList(): List<CommodityTradingInfo> = outBlock1?.map { it.toDomain() } ?: emptyList()

    companion object {
        private val log = LoggerFactory.getLogger(CommodityTradingPriceResponse::class.java)
        private val mapper = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        fun fromJson(json: String): CommodityTradingPriceResponse? {
            return try {
                val node = mapper.readTree(json)
                val response = CommodityTradingPriceResponse()
                if (node.isArray) {
                    response.outBlock1 = mapper.convertValue(node, object : TypeReference<List<OutBlock>>() {})
                } else if (node.has("OutBlock_1")) {
                    response.outBlock1 = mapper.convertValue(node.get("OutBlock_1"), object : TypeReference<List<OutBlock>>() {})
                } else {
                    log.warn("Unknown response format: $json")
                }
                response
            } catch (e: Exception) {
                log.error("Failed to parse JSON: $json", e)
                null
            }
        }
    }
}
