package com.trade.securities.application.port.out

interface LoadFscStockSubscriptionRightPort {
    fun loadSubscriptionRights(pageNo: Int, numOfRows: Int, basDt: String, itmsNm: String?, likeItmsNm: String?): Map<String, Any?>
}
