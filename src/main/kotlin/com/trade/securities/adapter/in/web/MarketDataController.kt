package com.trade.securities.adapter.`in`.web

import com.trade.securities.application.port.`in`.GetMarketDataUseCase
import com.trade.securities.domain.MarketData
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.DefaultValue
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType

@ApplicationScoped
@Path("/api/market-data")
@Produces(MediaType.APPLICATION_JSON)
class MarketDataController(
    private val getMarketDataUseCase: GetMarketDataUseCase
) {

    @GET
    fun getMarketData(
        @QueryParam("date") @DefaultValue("20241227") date: String,
        @QueryParam("type") @DefaultValue("KOSPI") type: String
    ): List<MarketData> {
        return getMarketDataUseCase.getMarketData(date, type)
    }
}
