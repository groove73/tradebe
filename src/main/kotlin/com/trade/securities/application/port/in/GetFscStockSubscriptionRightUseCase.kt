package com.trade.securities.application.port.`in`

interface GetFscStockSubscriptionRightUseCase {
    fun getSubscriptionRights(pageNo: Int, numOfRows: Int, basDt: String, itmsNm: String?, likeItmsNm: String?): Map<String, Any?>
}
