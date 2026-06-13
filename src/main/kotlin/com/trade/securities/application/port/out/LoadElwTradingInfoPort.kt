package com.trade.securities.application.port.out

import com.trade.securities.domain.ElwTradingInfo

interface LoadElwTradingInfoPort {
    fun loadElwTradingInfo(basDd: String): List<ElwTradingInfo>
}
