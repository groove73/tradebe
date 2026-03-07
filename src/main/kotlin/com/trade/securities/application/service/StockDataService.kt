package com.trade.securities.application.service

import com.trade.securities.application.port.`in`.LoadStockDataUseCase
import com.trade.securities.application.port.out.LoadMarketDataPort
import com.trade.securities.domain.StockData
import org.springframework.stereotype.Service

@Service
class StockDataService(
    private val loadMarketDataPort: LoadMarketDataPort
) : LoadStockDataUseCase {

    override fun getStockData(date: String, type: String): List<StockData> {
        return loadMarketDataPort.loadStockData(date, type)
    }
}
