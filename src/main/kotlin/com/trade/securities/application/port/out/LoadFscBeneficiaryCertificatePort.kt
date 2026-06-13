package com.trade.securities.application.port.out

interface LoadFscBeneficiaryCertificatePort {
    fun loadBeneficiaryCertificates(pageNo: Int, numOfRows: Int, basDt: String, itmsNm: String?, likeItmsNm: String?): Map<String, Any?>
}
