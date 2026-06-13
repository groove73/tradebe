package com.trade.securities.application.port.`in`

interface GetFscNewShareCertificateUseCase {
    fun getNewShareCertificates(pageNo: Int, numOfRows: Int, basDt: String, itmsNm: String?, likeItmsNm: String?): Map<String, Any?>
}
