package com.trade.securities.application.port.out

interface LoadFscNewShareCertificatePort {
    fun loadNewShareCertificates(pageNo: Int, numOfRows: Int, basDt: String, itmsNm: String?, likeItmsNm: String?): Map<String, Any?>
}
