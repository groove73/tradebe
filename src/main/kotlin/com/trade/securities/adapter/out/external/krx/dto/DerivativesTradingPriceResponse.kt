package com.trade.securities.adapter.out.external.krx.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.trade.securities.domain.DerivativesTradingInfo
import org.slf4j.LoggerFactory

data class DerivativesTradingPriceResponse(
    var outBlock1: List<OutBlock>? = null
) {
    @JsonProperty("OutBlock_1")
    fun setOutBlock1FromProperty(outBlock1: List<OutBlock>?) {
        this.outBlock1 = outBlock1
    }

    data class OutBlock(
        @JsonProperty("BAS_DD") val basDd: String? = null,
        @JsonProperty("PROD_NM") val prodNm: String? = null,
        @JsonProperty("MKT_NM") val mktNm: String? = null,
        @JsonProperty("ISU_CD") val isuCd: String? = null,
        @JsonProperty("ISU_NM") val isuNm: String? = null,
        @JsonProperty("TDD_CLSPRC") val tddClsprc: String? = null,
        @JsonProperty("CMPPREVDD_PRC") val cmpprevddPrc: String? = null,
        @JsonProperty("TDD_OPNPRC") val tddOpnprc: String? = null,
        @JsonProperty("TDD_HGPRC") val tddHgprc: String? = null,
        @JsonProperty("TDD_LWPRC") val tddLwprc: String? = null,
        @JsonProperty("ACC_TRDVOL") val accTrdvol: String? = null,
        @JsonProperty("ACC_TRDVAL") val accTrdval: String? = null,
        @JsonProperty("ACC_OPNINT_QTY") val accOpnintQty: String? = null,
        @JsonProperty("SPOT_PRC") val spotPrc: String? = null,
        @JsonProperty("SETL_PRC") val setlPrc: String? = null,
        @JsonProperty("RGHT_TP_NM") val rghtTpNm: String? = null,
        @JsonProperty("IMP_VOLT") val impVolt: String? = null,
        @JsonProperty("NXTD_BAS_PRC") val nxtdBasPrc: String? = null
    ) {
        fun toDomain(): DerivativesTradingInfo = DerivativesTradingInfo(
            basDd = basDd,
            prodNm = prodNm,
            mktNm = mktNm,
            isuCd = isuCd,
            isuNm = isuNm,
            tddClsprc = tddClsprc,
            cmpprevddPrc = cmpprevddPrc,
            tddOpnprc = tddOpnprc,
            tddHgprc = tddHgprc,
            tddLwprc = tddLwprc,
            accTrdvol = accTrdvol,
            accTrdval = accTrdval,
            accOpnintQty = accOpnintQty,
            spotPrc = spotPrc,
            setlPrc = setlPrc,
            rghtTpNm = rghtTpNm,
            impVolt = impVolt,
            nxtdBasPrc = nxtdBasPrc
        )
    }

    fun toDomainList(): List<DerivativesTradingInfo> = outBlock1?.map { it.toDomain() } ?: emptyList()

    companion object {
        private val log = LoggerFactory.getLogger(DerivativesTradingPriceResponse::class.java)
        private val mapper = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        fun fromJson(json: String): DerivativesTradingPriceResponse? {
            return try {
                val node = mapper.readTree(json)
                val response = DerivativesTradingPriceResponse()
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
