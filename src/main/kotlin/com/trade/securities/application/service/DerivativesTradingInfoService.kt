package com.trade.securities.application.service

import com.trade.securities.application.port.`in`.GetDerivativesTradingInfoUseCase
import com.trade.securities.application.port.out.LoadDerivativesTradingInfoPort
import com.trade.securities.domain.DerivativesTradingInfo
import org.springframework.stereotype.Service

@Service
class DerivativesTradingInfoService(
    private val loadDerivativesTradingInfoPort: LoadDerivativesTradingInfoPort
) : GetDerivativesTradingInfoUseCase {

    override fun getDerivativesTradingInfo(marketType: String, basDd: String): List<DerivativesTradingInfo> {
        return loadDerivativesTradingInfoPort.loadDerivativesTradingInfo(marketType, basDd)
    }
}
