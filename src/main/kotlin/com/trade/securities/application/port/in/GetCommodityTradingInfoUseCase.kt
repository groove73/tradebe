package com.trade.securities.application.port.`in`

import com.trade.securities.domain.CommodityTradingInfo

interface GetCommodityTradingInfoUseCase {
    fun getCommodityTradingInfo(marketType: String, basDd: String): List<CommodityTradingInfo>
}
