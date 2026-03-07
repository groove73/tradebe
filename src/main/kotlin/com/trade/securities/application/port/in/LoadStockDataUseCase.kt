package com.trade.securities.application.port.`in`

import com.trade.securities.domain.StockData

interface LoadStockDataUseCase {
    fun getStockData(date: String, type: String): List<StockData>
}
