package com.trade.securities.application.service

import com.trade.securities.application.port.`in`.GetFscStockPriceUseCase
import com.trade.securities.application.port.out.LoadFscStockPricePort
import org.springframework.stereotype.Service

@Service
class FscStockPriceService(
    private val loadFscStockPricePort: LoadFscStockPricePort
) : GetFscStockPriceUseCase {

    override fun getStockPrices(pageNo: Int, numOfRows: Int, basDt: String, itmsNm: String?, likeItmsNm: String?): Map<String, Any?> {
        return loadFscStockPricePort.loadStockPrices(pageNo, numOfRows, basDt, itmsNm, likeItmsNm)
    }
}
