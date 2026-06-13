package com.trade.securities.adapter.`in`.web

import com.trade.securities.application.port.`in`.GetElwTradingInfoUseCase
import com.trade.securities.domain.ElwTradingInfo
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType

@ApplicationScoped
@Path("/api/elw")
@Produces(MediaType.APPLICATION_JSON)
class ElwTradingInfoController(
    private val getElwTradingInfoUseCase: GetElwTradingInfoUseCase
) {

    @GET
    @Path("/trading-info")
    fun getElwTradingInfo(@QueryParam("basDd") basDd: String): List<ElwTradingInfo> {
        return getElwTradingInfoUseCase.getElwTradingInfo(basDd)
    }
}
