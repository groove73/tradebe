package com.trade.securities.application.service

import com.trade.securities.application.port.`in`.GetFscStockSubscriptionRightUseCase
import com.trade.securities.application.port.out.LoadFscStockSubscriptionRightPort
import org.springframework.stereotype.Service

@Service
class FscStockSubscriptionRightService(
    private val loadFscStockSubscriptionRightPort: LoadFscStockSubscriptionRightPort
) : GetFscStockSubscriptionRightUseCase {

    override fun getSubscriptionRights(pageNo: Int, numOfRows: Int, basDt: String, itmsNm: String?, likeItmsNm: String?): Map<String, Any?> {
        return loadFscStockSubscriptionRightPort.loadSubscriptionRights(pageNo, numOfRows, basDt, itmsNm, likeItmsNm)
    }
}
