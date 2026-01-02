package com.trade.securities.adapter.in.web;

import com.trade.securities.application.port.in.GetMarketDataUseCase;
import com.trade.securities.domain.MarketData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/market-data")
public class MarketDataController {

    private final GetMarketDataUseCase getMarketDataUseCase;

    @GetMapping
    public ResponseEntity<List<MarketData>> getMarketData(
            @org.springframework.web.bind.annotation.RequestParam(name = "date", defaultValue = "20241227") String date,
            @org.springframework.web.bind.annotation.RequestParam(name = "type", defaultValue = "KOSPI") String type) {
        return ResponseEntity.ok(getMarketDataUseCase.getMarketData(date, type));
    }
}
