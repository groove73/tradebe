package com.trade.securities.adapter.`in`.web

import com.trade.securities.application.port.`in`.GetEtfTradingInfoUseCase
import com.trade.securities.domain.EtfTradingInfo
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType

@ApplicationScoped
@Path("/api/etf")
@Produces(MediaType.APPLICATION_JSON)
class EtfTradingInfoController(
    private val getEtfTradingInfoUseCase: GetEtfTradingInfoUseCase
) {

    @GET
    @Path("/trading-info")
    fun getEtfTradingInfo(@QueryParam("basDd") basDd: String): List<EtfTradingInfo> {
        return getEtfTradingInfoUseCase.getEtfTradingInfo(basDd)
    }
}
