package com.trade.securities.application.service;

import com.trade.securities.application.port.in.LoadStockDataUseCase;
import com.trade.securities.application.port.out.LoadMarketDataPort;
import com.trade.securities.domain.StockData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockDataService implements LoadStockDataUseCase {

    private final LoadMarketDataPort loadMarketDataPort;

    @Override
    public List<StockData> getStockData(String date, String type) {
        return loadMarketDataPort.loadStockData(date, type);
    }
}
