package com.trade.securities.adapter.out.external.krx.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.trade.securities.domain.EtnTradingInfo

data class EtnTradingPriceResponse(
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
        @JsonProperty("PER1SECU_INDIC_VAL") val per1secuIndicVal: String? = null,
        @JsonProperty("TDD_OPNPRC") val tddOpnprc: String? = null,
        @JsonProperty("TDD_HGPRC") val hgprc: String? = null,
        @JsonProperty("TDD_LWPRC") val lwprc: String? = null,
        @JsonProperty("ACC_TRDVOL") val accTrdvol: String? = null,
        @JsonProperty("ACC_TRDVAL") val accTrdval: String? = null,
        @JsonProperty("MKTCAP") val mktcap: String? = null,
        @JsonProperty("INDIC_VAL_AMT") val indicValAmt: String? = null,
        @JsonProperty("LIST_SHRS") val listShrs: String? = null,
        @JsonProperty("IDX_IND_NM") val idxIndNm: String? = null,
        @JsonProperty("OBJ_STKPRC_IDX") val objStkprcIdx: String? = null,
        @JsonProperty("CMPPREVDD_IDX") val cmpprevddIdx: String? = null,
        @JsonProperty("FLUC_RT_IDX") val flucRtIdx: String? = null
    ) {
        fun toDomain(): EtnTradingInfo = EtnTradingInfo(
            basDd = basDd,
            isuCd = isuCd,
            isuNm = isuNm,
            tddClsprc = tddClsprc,
            cmpprevddPrc = cmpprevddPrc,
            flucRt = flucRt,
            per1secuIndicVal = per1secuIndicVal,
            tddOpnprc = tddOpnprc,
            tddHgprc = hgprc,
            tddLwprc = lwprc,
            accTrdvol = accTrdvol,
            accTrdval = accTrdval,
            mktcap = mktcap,
            indicValAmt = indicValAmt,
            listShrs = listShrs,
            idxIndNm = idxIndNm,
            objStkprcIdx = objStkprcIdx,
            cmpprevddIdx = cmpprevddIdx,
            flucRtIdx = flucRtIdx
        )
    }

    fun toDomainList(): List<EtnTradingInfo> = outBlock1?.map { it.toDomain() } ?: emptyList()
}
