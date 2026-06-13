package com.trade.securities.adapter.`in`.web

import com.trade.securities.application.port.`in`.GetDerivativesTradingInfoUseCase
import com.trade.securities.domain.DerivativesTradingInfo
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType

@ApplicationScoped
@Path("/api/derivatives")
@Produces(MediaType.APPLICATION_JSON)
class DerivativesTradingInfoController(
    private val getDerivativesTradingInfoUseCase: GetDerivativesTradingInfoUseCase
) {

    @GET
    @Path("/trading-info/{marketType}")
    fun getDerivativesTradingInfo(
        @PathParam("marketType") marketType: String,
        @QueryParam("basDd") basDd: String
    ): List<DerivativesTradingInfo> {
        return getDerivativesTradingInfoUseCase.getDerivativesTradingInfo(marketType, basDd)
    }
}
