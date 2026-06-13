package com.trade.securities.adapter.out.external.krx.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.trade.securities.domain.BondTradingInfo

data class BondTradingPriceResponse(
    @JsonProperty("OutBlock_1")
    val outBlock1: List<OutBlock>? = null
) {
    data class OutBlock(
        @JsonProperty("BAS_DD") val basDd: String? = null,
        @JsonProperty("MKT_NM") val mktNm: String? = null,
        @JsonProperty("ISU_CD") val isuCd: String? = null,
        @JsonProperty("ISU_NM") val isuNm: String? = null,
        @JsonProperty("CLSPRC") val clsprc: String? = null,
        @JsonProperty("CMPPREVDD_PRC") val cmpprevddPrc: String? = null,
        @JsonProperty("CLSPRC_YD") val clsprcYd: String? = null,
        @JsonProperty("OPNPRC") val opnprc: String? = null,
        @JsonProperty("OPNPRC_YD") val opnprcYd: String? = null,
        @JsonProperty("HGPRC") val hgprc: String? = null,
        @JsonProperty("HGPRC_YD") val hgprcYd: String? = null,
        @JsonProperty("LWPRC") val lwprc: String? = null,
        @JsonProperty("LWPRC_YD") val lwprcYd: String? = null,
        @JsonProperty("ACC_TRDVOL") val accTrdvol: String? = null,
        @JsonProperty("ACC_TRDVAL") val accTrdval: String? = null,
        @JsonProperty("MKTCAP") val mktcap: String? = null,
        @JsonProperty("LIST_SHRS") val listShrs: String? = null,
        @JsonProperty("BND_EXP_TP_NM") val bndExpTpNm: String? = null,
        @JsonProperty("GOVBND_ISU_TP_NM") val govbndIsuTpNm: String? = null
    ) {
        fun toDomain(): BondTradingInfo = BondTradingInfo(
            basDd = basDd,
            mktNm = mktNm,
            isuCd = isuCd,
            isuNm = isuNm,
            clsprc = clsprc,
            cmpprevddPrc = cmpprevddPrc,
            clsprcYd = clsprcYd,
            opnprc = opnprc,
            opnprcYd = opnprcYd,
            hgprc = hgprc,
            hgprcYd = hgprcYd,
            lwprc = lwprc,
            lwprcYd = lwprcYd,
            accTrdvol = accTrdvol,
            accTrdval = accTrdval,
            mktcap = mktcap,
            listShrs = listShrs,
            bndExpTpNm = bndExpTpNm,
            govbndIsuTpNm = govbndIsuTpNm
        )
    }

    fun toDomainList(): List<BondTradingInfo> = outBlock1?.map { it.toDomain() } ?: emptyList()
}
