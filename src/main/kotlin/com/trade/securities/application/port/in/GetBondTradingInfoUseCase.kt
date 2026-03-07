package com.trade.securities.application.port.`in`

import com.trade.securities.domain.BondTradingInfo

interface GetBondTradingInfoUseCase {
    fun getBondTradingInfo(marketType: String, basDd: String): List<BondTradingInfo>
}
