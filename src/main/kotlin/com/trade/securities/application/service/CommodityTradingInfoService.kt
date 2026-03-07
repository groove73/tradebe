package com.trade.securities.application.service

import com.trade.securities.application.port.`in`.GetCommodityTradingInfoUseCase
import com.trade.securities.application.port.out.LoadCommodityTradingInfoPort
import com.trade.securities.domain.CommodityTradingInfo
import org.springframework.stereotype.Service

@Service
class CommodityTradingInfoService(
    private val loadCommodityTradingInfoPort: LoadCommodityTradingInfoPort
) : GetCommodityTradingInfoUseCase {

    override fun getCommodityTradingInfo(marketType: String, basDd: String): List<CommodityTradingInfo> {
        return loadCommodityTradingInfoPort.loadCommodityTradingInfo(marketType, basDd)
    }
}
