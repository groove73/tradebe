package com.trade.securities.application.service

import com.trade.securities.application.port.`in`.GetBondTradingInfoUseCase
import com.trade.securities.application.port.out.LoadBondTradingInfoPort
import com.trade.securities.domain.BondTradingInfo
import org.springframework.stereotype.Service

@Service
class BondTradingInfoService(
    private val loadBondTradingInfoPort: LoadBondTradingInfoPort
) : GetBondTradingInfoUseCase {

    override fun getBondTradingInfo(marketType: String, basDd: String): List<BondTradingInfo> {
        return loadBondTradingInfoPort.loadBondTradingInfo(marketType, basDd)
    }
}
