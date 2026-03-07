package com.trade.securities.adapter.`in`.web

import com.trade.securities.application.port.`in`.GetCommodityTradingInfoUseCase
import com.trade.securities.domain.CommodityTradingInfo
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/commodities")
class CommodityTradingInfoController(
    private val getCommodityTradingInfoUseCase: GetCommodityTradingInfoUseCase
) {

    @GetMapping("/trading-info/{marketType}")
    fun getCommodityTradingInfo(
        @PathVariable marketType: String,
        @RequestParam basDd: String
    ): List<CommodityTradingInfo> {
        return getCommodityTradingInfoUseCase.getCommodityTradingInfo(marketType, basDd)
    }
}
