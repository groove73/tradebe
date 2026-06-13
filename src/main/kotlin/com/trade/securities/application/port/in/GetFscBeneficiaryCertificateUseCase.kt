package com.trade.securities.application.port.`in`

interface GetFscBeneficiaryCertificateUseCase {
    fun getBeneficiaryCertificates(pageNo: Int, numOfRows: Int, basDt: String, itmsNm: String?, likeItmsNm: String?): Map<String, Any?>
}
