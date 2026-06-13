package com.trade.securities.application.port.out

import com.trade.securities.domain.EtfTradingInfo

interface LoadEtfTradingInfoPort {
    fun loadEtfTradingInfo(basDd: String): List<EtfTradingInfo>
}
