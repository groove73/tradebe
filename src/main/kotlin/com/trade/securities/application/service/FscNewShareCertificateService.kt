package com.trade.securities.application.service

import com.trade.securities.application.port.`in`.GetFscNewShareCertificateUseCase
import com.trade.securities.application.port.out.LoadFscNewShareCertificatePort
import org.springframework.stereotype.Service

@Service
class FscNewShareCertificateService(
    private val loadFscNewShareCertificatePort: LoadFscNewShareCertificatePort
) : GetFscNewShareCertificateUseCase {

    override fun getNewShareCertificates(pageNo: Int, numOfRows: Int, basDt: String, itmsNm: String?, likeItmsNm: String?): Map<String, Any?> {
        return loadFscNewShareCertificatePort.loadNewShareCertificates(pageNo, numOfRows, basDt, itmsNm, likeItmsNm)
    }
}
