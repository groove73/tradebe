package com.trade.securities.application.port.out

interface LoadFscStockPricePort {
    fun loadStockPrices(pageNo: Int, numOfRows: Int, basDt: String, itmsNm: String?, likeItmsNm: String?): Map<String, Any?>
}
