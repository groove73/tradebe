package com.trade.securities.application.port.out

import com.trade.securities.domain.EtnTradingInfo

interface LoadEtnTradingInfoPort {
    fun loadEtnTradingInfo(basDd: String): List<EtnTradingInfo>
}
