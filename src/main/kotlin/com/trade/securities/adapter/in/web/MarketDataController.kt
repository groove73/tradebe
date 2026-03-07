package com.trade.securities.adapter.`in`.web

import com.trade.securities.application.port.`in`.GetMarketDataUseCase
import com.trade.securities.domain.MarketData
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/market-data")
class MarketDataController(
    private val getMarketDataUseCase: GetMarketDataUseCase
) {

    @GetMapping
    fun getMarketData(
        @RequestParam(name = "date", defaultValue = "20241227") date: String,
        @RequestParam(name = "type", defaultValue = "KOSPI") type: String
    ): ResponseEntity<List<MarketData>> {
        return ResponseEntity.ok(getMarketDataUseCase.getMarketData(date, type))
    }
}
