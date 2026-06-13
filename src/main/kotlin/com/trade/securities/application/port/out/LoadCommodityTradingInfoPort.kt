package com.trade.securities.application.port.out

import com.trade.securities.domain.CommodityTradingInfo

interface LoadCommodityTradingInfoPort {
    fun loadCommodityTradingInfo(marketType: String, basDd: String): List<CommodityTradingInfo>
}
