package com.trade.securities.application.service

import com.trade.securities.application.port.`in`.GetElwTradingInfoUseCase
import com.trade.securities.application.port.out.LoadElwTradingInfoPort
import com.trade.securities.domain.ElwTradingInfo
import org.springframework.stereotype.Service

@Service
class ElwTradingInfoService(
    private val loadElwTradingInfoPort: LoadElwTradingInfoPort
) : GetElwTradingInfoUseCase {

    override fun getElwTradingInfo(basDd: String): List<ElwTradingInfo> {
        return loadElwTradingInfoPort.loadElwTradingInfo(basDd)
    }
}
