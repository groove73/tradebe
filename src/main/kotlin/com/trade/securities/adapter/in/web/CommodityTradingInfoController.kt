package com.trade.securities.adapter.`in`.web

import com.trade.securities.application.port.`in`.GetCommodityTradingInfoUseCase
import com.trade.securities.domain.CommodityTradingInfo
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType

@ApplicationScoped
@Path("/api/commodities")
@Produces(MediaType.APPLICATION_JSON)
class CommodityTradingInfoController(
    private val getCommodityTradingInfoUseCase: GetCommodityTradingInfoUseCase
) {

    @GET
    @Path("/trading-info/{marketType}")
    fun getCommodityTradingInfo(
        @PathParam("marketType") marketType: String,
        @QueryParam("basDd") basDd: String
    ): List<CommodityTradingInfo> {
        return getCommodityTradingInfoUseCase.getCommodityTradingInfo(marketType, basDd)
    }
}
