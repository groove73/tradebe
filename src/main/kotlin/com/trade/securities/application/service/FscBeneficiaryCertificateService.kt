package com.trade.securities.application.service

import com.trade.securities.application.port.`in`.GetFscBeneficiaryCertificateUseCase
import com.trade.securities.application.port.out.LoadFscBeneficiaryCertificatePort
import org.springframework.stereotype.Service

@Service
class FscBeneficiaryCertificateService(
    private val loadFscBeneficiaryCertificatePort: LoadFscBeneficiaryCertificatePort
) : GetFscBeneficiaryCertificateUseCase {

    override fun getBeneficiaryCertificates(pageNo: Int, numOfRows: Int, basDt: String, itmsNm: String?, likeItmsNm: String?): Map<String, Any?> {
        return loadFscBeneficiaryCertificatePort.loadBeneficiaryCertificates(pageNo, numOfRows, basDt, itmsNm, likeItmsNm)
    }
}
