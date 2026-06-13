package com.trade.securities.adapter.`in`.web

import com.trade.securities.application.port.`in`.GetBondTradingInfoUseCase
import com.trade.securities.domain.BondTradingInfo
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType

@ApplicationScoped
@Path("/api/bond")
@Produces(MediaType.APPLICATION_JSON)
class BondTradingInfoController(
    private val getBondTradingInfoUseCase: GetBondTradingInfoUseCase
) {

    @GET
    @Path("/trading-info/{marketType}")
    fun getBondTradingInfo(
        @PathParam("marketType") marketType: String,
        @QueryParam("basDd") basDd: String
    ): List<BondTradingInfo> {
        return getBondTradingInfoUseCase.getBondTradingInfo(marketType, basDd)
    }
}
