package com.trade.securities.application.port.out

import com.trade.securities.domain.MarketData
import com.trade.securities.domain.StockData

interface LoadMarketDataPort {
    fun loadMarketData(date: String, type: String): List<MarketData>
    fun loadStockData(date: String, type: String): List<StockData>
}
