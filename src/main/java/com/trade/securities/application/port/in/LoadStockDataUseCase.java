package com.trade.securities.application.port.in;

import com.trade.securities.domain.StockData;
import java.util.List;

public interface LoadStockDataUseCase {
    List<StockData> getStockData(String date, String type);
}
