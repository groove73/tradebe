package com.trade.securities.adapter.`in`.web

import com.trade.securities.application.port.`in`.GetEtnTradingInfoUseCase
import com.trade.securities.domain.EtnTradingInfo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/etn")
class EtnTradingInfoController(
    private val getEtnTradingInfoUseCase: GetEtnTradingInfoUseCase
) {

    @GetMapping("/trading-info")
    fun getEtnTradingInfo(@RequestParam basDd: String): List<EtnTradingInfo> {
        return getEtnTradingInfoUseCase.getEtnTradingInfo(basDd)
    }
}
