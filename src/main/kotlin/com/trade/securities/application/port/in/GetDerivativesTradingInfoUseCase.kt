package com.trade.securities.application.port.`in`

import com.trade.securities.domain.DerivativesTradingInfo

interface GetDerivativesTradingInfoUseCase {
    fun getDerivativesTradingInfo(marketType: String, basDd: String): List<DerivativesTradingInfo>
}
