package com.trade.securities.application.port.in;

import com.trade.securities.domain.MarketData;
import java.util.List;

public interface GetMarketDataUseCase {
    List<MarketData> getMarketData(String date, String type);
}
