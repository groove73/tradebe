package com.trade.securities.adapter.`in`.web

import com.trade.securities.application.port.`in`.GetEtfTradingInfoUseCase
import com.trade.securities.domain.EtfTradingInfo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/etf")
class EtfTradingInfoController(
    private val getEtfTradingInfoUseCase: GetEtfTradingInfoUseCase
) {

    @GetMapping("/trading-info")
    fun getEtfTradingInfo(@RequestParam basDd: String): List<EtfTradingInfo> {
        return getEtfTradingInfoUseCase.getEtfTradingInfo(basDd)
    }
}
