package com.trade.securities.application.service

import com.trade.securities.application.port.`in`.GetEtnTradingInfoUseCase
import com.trade.securities.application.port.out.LoadEtnTradingInfoPort
import com.trade.securities.domain.EtnTradingInfo
import org.springframework.stereotype.Service

@Service
class EtnTradingInfoService(
    private val loadEtnTradingInfoPort: LoadEtnTradingInfoPort
) : GetEtnTradingInfoUseCase {

    override fun getEtnTradingInfo(basDd: String): List<EtnTradingInfo> {
        return loadEtnTradingInfoPort.loadEtnTradingInfo(basDd)
    }
}
