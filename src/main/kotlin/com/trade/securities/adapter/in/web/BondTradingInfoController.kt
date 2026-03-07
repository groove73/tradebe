package com.trade.securities.adapter.`in`.web

import com.trade.securities.application.port.`in`.GetBondTradingInfoUseCase
import com.trade.securities.domain.BondTradingInfo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/bond")
class BondTradingInfoController(
    private val getBondTradingInfoUseCase: GetBondTradingInfoUseCase
) {

    @GetMapping("/trading-info/{marketType}")
    fun getBondTradingInfo(
        @PathVariable marketType: String,
        @RequestParam basDd: String
    ): List<BondTradingInfo> {
        return getBondTradingInfoUseCase.getBondTradingInfo(marketType, basDd)
    }
}
