package com.trade.securities.application.service

import com.trade.securities.application.port.`in`.GetMarketDataUseCase
import com.trade.securities.application.port.out.LoadMarketDataPort
import com.trade.securities.domain.MarketData
import org.springframework.stereotype.Service

@Service
class MarketDataService(
    private val loadMarketDataPort: LoadMarketDataPort
) : GetMarketDataUseCase {

    override fun getMarketData(date: String, type: String): List<MarketData> {
        return loadMarketDataPort.loadMarketData(date, type)
    }
}
