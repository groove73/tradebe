package com.trade.securities.application.port.out

import com.trade.securities.domain.DerivativesTradingInfo

interface LoadDerivativesTradingInfoPort {
    fun loadDerivativesTradingInfo(marketType: String, basDd: String): List<DerivativesTradingInfo>
}
