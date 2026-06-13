package com.trade.securities.application.port.out

import com.trade.securities.domain.BondTradingInfo

interface LoadBondTradingInfoPort {
    fun loadBondTradingInfo(marketType: String, basDd: String): List<BondTradingInfo>
}
