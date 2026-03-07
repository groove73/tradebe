package com.trade.securities.adapter.out.external.krx.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.trade.securities.domain.ElwTradingInfo

data class ElwTradingPriceResponse(
    @JsonProperty("OutBlock_1")
    val outBlock1: List<OutBlock>? = null
) {
    data class OutBlock(
        @JsonProperty("BAS_DD") val basDd: String? = null,
        @JsonProperty("ISU_CD") val isuCd: String? = null,
        @JsonProperty("ISU_NM") val isuNm: String? = null,
        @JsonProperty("TDD_CLSPRC") val tddClsprc: String? = null,
        @JsonProperty("CMPPREVDD_PRC") val cmpprevddPrc: String? = null,
        @JsonProperty("TDD_OPNPRC") val tddOpnprc: String? = null,
        @JsonProperty("TDD_HGPRC") val tddHgprc: String? = null,
        @JsonProperty("TDD_LWPRC") val tddLwprc: String? = null,
        @JsonProperty("ACC_TRDVOL") val accTrdvol: String? = null,
        @JsonProperty("ACC_TRDVAL") val accTrdval: String? = null,
        @JsonProperty("MKTCAP") val mktcap: String? = null,
        @JsonProperty("LIST_SHRS") val listShrs: String? = null,
        @JsonProperty("ULY_NM") val ulyNm: String? = null,
        @JsonProperty("ULY_PRC") val ulyPrc: String? = null,
        @JsonProperty("CMPPREVDD_PRC_ULY") val cmpprevddPrcUly: String? = null,
        @JsonProperty("FLUC_RT_ULY") val flucRtUly: String? = null
    ) {
        fun toDomain(): ElwTradingInfo = ElwTradingInfo(
            basDd = basDd,
            isuCd = isuCd,
            isuNm = isuNm,
            tddClsprc = tddClsprc,
            cmpprevddPrc = cmpprevddPrc,
            tddOpnprc = tddOpnprc,
            tddHgprc = tddHgprc,
            tddLwprc = tddLwprc,
            accTrdvol = accTrdvol,
            accTrdval = accTrdval,
            mktcap = mktcap,
            listShrs = listShrs,
            ulyNm = ulyNm,
            ulyPrc = ulyPrc,
            cmpprevddPrcUly = cmpprevddPrcUly,
            flucRtUly = flucRtUly
        )
    }

    fun toDomainList(): List<ElwTradingInfo> = outBlock1?.map { it.toDomain() } ?: emptyList()
}
