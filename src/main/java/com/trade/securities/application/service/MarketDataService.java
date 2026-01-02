package com.trade.securities.application.service;

import com.trade.securities.application.port.in.GetMarketDataUseCase;
import com.trade.securities.application.port.out.LoadMarketDataPort;
import com.trade.securities.domain.MarketData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketDataService implements GetMarketDataUseCase {

    private final LoadMarketDataPort loadMarketDataPort;

    @Override
    public List<MarketData> getMarketData(String date, String type) {
        return loadMarketDataPort.loadMarketData(date, type);
    }
}
