package com.trade.securities.adapter.`in`.web

import com.trade.securities.application.port.`in`.LoadStockDataUseCase
import com.trade.securities.domain.StockData
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.DefaultValue
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType

@ApplicationScoped
@Path("/api/stock-data")
@Produces(MediaType.APPLICATION_JSON)
class StockDataController(
    private val loadStockDataUseCase: LoadStockDataUseCase
) {

    @GET
    fun getStockData(
        @QueryParam("date") date: String,
        @QueryParam("type") @DefaultValue("KOSDAQ") type: String
    ): List<StockData> {
        return loadStockDataUseCase.getStockData(date, type)
    }
}
