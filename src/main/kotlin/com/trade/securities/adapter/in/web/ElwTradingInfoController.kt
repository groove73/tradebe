package com.trade.securities.adapter.`in`.web

import com.trade.securities.application.port.`in`.GetElwTradingInfoUseCase
import com.trade.securities.domain.ElwTradingInfo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/elw")
class ElwTradingInfoController(
    private val getElwTradingInfoUseCase: GetElwTradingInfoUseCase
) {

    @GetMapping("/trading-info")
    fun getElwTradingInfo(@RequestParam basDd: String): List<ElwTradingInfo> {
        return getElwTradingInfoUseCase.getElwTradingInfo(basDd)
    }
}
