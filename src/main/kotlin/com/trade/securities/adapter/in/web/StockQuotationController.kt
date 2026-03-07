package com.trade.securities.adapter.`in`.web

import com.trade.securities.application.port.`in`.GetFscBeneficiaryCertificateUseCase
import com.trade.securities.application.port.`in`.GetFscNewShareCertificateUseCase
import com.trade.securities.application.port.`in`.GetFscStockPriceUseCase
import com.trade.securities.application.port.`in`.GetFscStockSubscriptionRightUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/stock-quotation")
class StockQuotationController(
    private val getFscStockPriceUseCase: GetFscStockPriceUseCase,
    private val getFscNewShareCertificateUseCase: GetFscNewShareCertificateUseCase,
    private val getFscBeneficiaryCertificateUseCase: GetFscBeneficiaryCertificateUseCase,
    private val getFscStockSubscriptionRightUseCase: GetFscStockSubscriptionRightUseCase
) {

    @GetMapping("/price")
    fun getStockPrices(
        @RequestParam(defaultValue = "1") pageNo: Int,
        @RequestParam(defaultValue = "10") numOfRows: Int,
        @RequestParam basDt: String,
        @RequestParam(required = false) itmsNm: String?,
        @RequestParam(required = false) likeItmsNm: String?
    ): Map<String, Any?> {
        return getFscStockPriceUseCase.getStockPrices(pageNo, numOfRows, basDt, itmsNm, likeItmsNm)
    }

    @GetMapping("/new-share")
    fun getNewShareCertificates(
        @RequestParam(defaultValue = "1") pageNo: Int,
        @RequestParam(defaultValue = "10") numOfRows: Int,
        @RequestParam basDt: String,
        @RequestParam(required = false) itmsNm: String?,
        @RequestParam(required = false) likeItmsNm: String?
    ): Map<String, Any?> {
        return getFscNewShareCertificateUseCase.getNewShareCertificates(pageNo, numOfRows, basDt, itmsNm, likeItmsNm)
    }

    @GetMapping("/beneficiary")
    fun getBeneficiaryCertificates(
        @RequestParam(defaultValue = "1") pageNo: Int,
        @RequestParam(defaultValue = "10") numOfRows: Int,
        @RequestParam basDt: String,
        @RequestParam(required = false) itmsNm: String?,
        @RequestParam(required = false) likeItmsNm: String?
    ): Map<String, Any?> {
        return getFscBeneficiaryCertificateUseCase.getBeneficiaryCertificates(pageNo, numOfRows, basDt, itmsNm, likeItmsNm)
    }

    @GetMapping("/subscription-right")
    fun getSubscriptionRights(
        @RequestParam(defaultValue = "1") pageNo: Int,
        @RequestParam(defaultValue = "10") numOfRows: Int,
        @RequestParam basDt: String,
        @RequestParam(required = false) itmsNm: String?,
        @RequestParam(required = false) likeItmsNm: String?
    ): Map<String, Any?> {
        return getFscStockSubscriptionRightUseCase.getSubscriptionRights(pageNo, numOfRows, basDt, itmsNm, likeItmsNm)
    }
}
