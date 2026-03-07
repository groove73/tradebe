package com.trade.securities.adapter.`in`.web

import com.trade.securities.application.port.`in`.GetDerivativesTradingInfoUseCase
import com.trade.securities.domain.DerivativesTradingInfo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/derivatives")
class DerivativesTradingInfoController(
    private val getDerivativesTradingInfoUseCase: GetDerivativesTradingInfoUseCase
) {

    @GetMapping("/trading-info/{marketType}")
    fun getDerivativesTradingInfo(
        @PathVariable marketType: String,
        @RequestParam basDd: String
    ): List<DerivativesTradingInfo> {
        return getDerivativesTradingInfoUseCase.getDerivativesTradingInfo(marketType, basDd)
    }
}
