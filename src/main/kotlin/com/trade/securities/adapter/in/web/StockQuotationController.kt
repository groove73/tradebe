package com.trade.securities.adapter.`in`.web

import com.trade.securities.application.port.`in`.GetFscBeneficiaryCertificateUseCase
import com.trade.securities.application.port.`in`.GetFscNewShareCertificateUseCase
import com.trade.securities.application.port.`in`.GetFscStockPriceUseCase
import com.trade.securities.application.port.`in`.GetFscStockSubscriptionRightUseCase
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.DefaultValue
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType

@ApplicationScoped
@Path("/api/stock-quotation")
@Produces(MediaType.APPLICATION_JSON)
class StockQuotationController(
    private val getFscStockPriceUseCase: GetFscStockPriceUseCase,
    private val getFscNewShareCertificateUseCase: GetFscNewShareCertificateUseCase,
    private val getFscBeneficiaryCertificateUseCase: GetFscBeneficiaryCertificateUseCase,
    private val getFscStockSubscriptionRightUseCase: GetFscStockSubscriptionRightUseCase
) {

    @GET
    @Path("/price")
    fun getStockPrices(
        @QueryParam("pageNo") @DefaultValue("1") pageNo: Int,
        @QueryParam("numOfRows") @DefaultValue("10") numOfRows: Int,
        @QueryParam("basDt") basDt: String,
        @QueryParam("itmsNm") itmsNm: String?,
        @QueryParam("likeItmsNm") likeItmsNm: String?
    ): Map<String, Any?> {
        return getFscStockPriceUseCase.getStockPrices(pageNo, numOfRows, basDt, itmsNm, likeItmsNm)
    }

    @GET
    @Path("/new-share")
    fun getNewShareCertificates(
        @QueryParam("pageNo") @DefaultValue("1") pageNo: Int,
        @QueryParam("numOfRows") @DefaultValue("10") numOfRows: Int,
        @QueryParam("basDt") basDt: String,
        @QueryParam("itmsNm") itmsNm: String?,
        @QueryParam("likeItmsNm") likeItmsNm: String?
    ): Map<String, Any?> {
        return getFscNewShareCertificateUseCase.getNewShareCertificates(pageNo, numOfRows, basDt, itmsNm, likeItmsNm)
    }

    @GET
    @Path("/beneficiary")
    fun getBeneficiaryCertificates(
        @QueryParam("pageNo") @DefaultValue("1") pageNo: Int,
        @QueryParam("numOfRows") @DefaultValue("10") numOfRows: Int,
        @QueryParam("basDt") basDt: String,
        @QueryParam("itmsNm") itmsNm: String?,
        @QueryParam("likeItmsNm") likeItmsNm: String?
    ): Map<String, Any?> {
        return getFscBeneficiaryCertificateUseCase.getBeneficiaryCertificates(pageNo, numOfRows, basDt, itmsNm, likeItmsNm)
    }

    @GET
    @Path("/subscription-right")
    fun getSubscriptionRights(
        @QueryParam("pageNo") @DefaultValue("1") pageNo: Int,
        @QueryParam("numOfRows") @DefaultValue("10") numOfRows: Int,
        @QueryParam("basDt") basDt: String,
        @QueryParam("itmsNm") itmsNm: String?,
        @QueryParam("likeItmsNm") likeItmsNm: String?
    ): Map<String, Any?> {
        return getFscStockSubscriptionRightUseCase.getSubscriptionRights(pageNo, numOfRows, basDt, itmsNm, likeItmsNm)
    }
}
