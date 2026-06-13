package com.trade.securities.application.service

import com.trade.securities.application.port.`in`.GetEtfTradingInfoUseCase
import com.trade.securities.application.port.out.LoadEtfTradingInfoPort
import com.trade.securities.domain.EtfTradingInfo
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class EtfTradingInfoService(
    private val loadEtfTradingInfoPort: LoadEtfTradingInfoPort
) : GetEtfTradingInfoUseCase {

    override fun getEtfTradingInfo(basDd: String): List<EtfTradingInfo> {
        return loadEtfTradingInfoPort.loadEtfTradingInfo(basDd)
    }
}
