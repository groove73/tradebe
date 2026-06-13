package com.trade.securities.application.port.`in`

interface GetFscStockPriceUseCase {
    fun getStockPrices(pageNo: Int, numOfRows: Int, basDt: String, itmsNm: String?, likeItmsNm: String?): Map<String, Any?>
}
