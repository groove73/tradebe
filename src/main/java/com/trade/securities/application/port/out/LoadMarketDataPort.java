package com.trade.securities.application.port.out;

import com.trade.securities.domain.MarketData;
import com.trade.securities.domain.StockData;
import java.util.List;

public interface LoadMarketDataPort {
    List<MarketData> loadMarketData(String date, String type);

    List<StockData> loadStockData(String date, String type);
}
