package com.trade.securities.application.port.`in`

import com.trade.securities.domain.MarketData

interface GetMarketDataUseCase {
    fun getMarketData(date: String, type: String): List<MarketData>
}
