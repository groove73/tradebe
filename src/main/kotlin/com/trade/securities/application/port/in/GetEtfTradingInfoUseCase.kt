package com.trade.securities.application.port.`in`

import com.trade.securities.domain.EtfTradingInfo

interface GetEtfTradingInfoUseCase {
    fun getEtfTradingInfo(basDd: String): List<EtfTradingInfo>
}
