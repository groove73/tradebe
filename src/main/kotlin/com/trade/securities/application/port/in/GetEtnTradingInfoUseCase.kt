package com.trade.securities.application.port.`in`

import com.trade.securities.domain.EtnTradingInfo

interface GetEtnTradingInfoUseCase {
    fun getEtnTradingInfo(basDd: String): List<EtnTradingInfo>
}
