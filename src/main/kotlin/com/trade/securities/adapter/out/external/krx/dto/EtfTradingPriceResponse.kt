package com.trade.securities.adapter.out.external.krx.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.trade.securities.domain.EtfTradingInfo

data class EtfTradingPriceResponse(
    @JsonProperty("OutBlock_1")
    val outBlock1: List<OutBlock>? = null
) {
    data class OutBlock(
        @JsonProperty("BAS_DD") val basDd: String? = null,
        @JsonProperty("ISU_CD") val isuCd: String? = null,
        @JsonProperty("ISU_NM") val isuNm: String? = null,
        @JsonProperty("TDD_CLSPRC") val tddClsprc: String? = null,
        @JsonProperty("CMPPREVDD_PRC") val cmpprevddPrc: String? = null,
        @JsonProperty("FLUC_RT") val flucRt: String? = null,
        @JsonProperty("NAV") val nav: String? = null,
        @JsonProperty("TDD_OPNPRC") val tddOpnprc: String? = null,
        @JsonProperty("TDD_HGPRC") val tddHgprc: String? = null,
        @JsonProperty("TDD_LWPRC") val tddLwprc: String? = null,
        @JsonProperty("ACC_TRDVOL") val accTrdvol: String? = null,
        @JsonProperty("ACC_TRDVAL") val accTrdval: String? = null,
        @JsonProperty("MKTCAP") val mktcap: String? = null,
        @JsonProperty("INVSTASST_NETASST_TOTAMT") val invstasstNetasstTotamt: String? = null,
        @JsonProperty("LIST_SHRS") val listShrs: String? = null,
        @JsonProperty("IDX_IND_NM") val idxIndNm: String? = null,
        @JsonProperty("OBJ_STKPRC_IDX") val objStkprcIdx: String? = null,
        @JsonProperty("CMPPREVDD_IDX") val cmpprevddIdx: String? = null,
        @JsonProperty("FLUC_RT_IDX") val flucRtIdx: String? = null
    ) {
        fun toDomain(): EtfTradingInfo = EtfTradingInfo(
            basDd = basDd,
            isuCd = isuCd,
            isuNm = isuNm,
            tddClsprc = tddClsprc,
            cmpprevddPrc = cmpprevddPrc,
            flucRt = flucRt,
            nav = nav,
            tddOpnprc = tddOpnprc,
            tddHgprc = tddHgprc,
            tddLwprc = tddLwprc,
            accTrdvol = accTrdvol,
            accTrdval = accTrdval,
            mktcap = mktcap,
            invstasstNetasstTotamt = invstasstNetasstTotamt,
            listShrs = listShrs,
            idxIndNm = idxIndNm,
            objStkprcIdx = objStkprcIdx,
            cmpprevddIdx = cmpprevddIdx,
            flucRtIdx = flucRtIdx
        )
    }

    fun toDomainList(): List<EtfTradingInfo> = outBlock1?.map { it.toDomain() } ?: emptyList()
}
