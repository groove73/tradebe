package com.trade.securities.adapter.`in`.web

import com.trade.securities.application.port.`in`.LoadStockDataUseCase
import com.trade.securities.domain.StockData
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/stock-data")
class StockDataController(
    private val loadStockDataUseCase: LoadStockDataUseCase
) {

    @GetMapping
    fun getStockData(
        @RequestParam date: String,
        @RequestParam(defaultValue = "KOSDAQ") type: String
    ): List<StockData> {
        return loadStockDataUseCase.getStockData(date, type)
    }
}
