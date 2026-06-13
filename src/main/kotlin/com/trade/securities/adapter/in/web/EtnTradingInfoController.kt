package com.trade.securities.adapter.`in`.web

import com.trade.securities.application.port.`in`.GetEtnTradingInfoUseCase
import com.trade.securities.domain.EtnTradingInfo
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType

@ApplicationScoped
@Path("/api/etn")
@Produces(MediaType.APPLICATION_JSON)
class EtnTradingInfoController(
    private val getEtnTradingInfoUseCase: GetEtnTradingInfoUseCase
) {

    @GET
    @Path("/trading-info")
    fun getEtnTradingInfo(@QueryParam("basDd") basDd: String): List<EtnTradingInfo> {
        return getEtnTradingInfoUseCase.getEtnTradingInfo(basDd)
    }
}
