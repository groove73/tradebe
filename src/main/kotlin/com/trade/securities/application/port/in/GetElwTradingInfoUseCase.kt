package com.trade.securities.application.port.`in`

import com.trade.securities.domain.ElwTradingInfo

interface GetElwTradingInfoUseCase {
    fun getElwTradingInfo(basDd: String): List<ElwTradingInfo>
}
