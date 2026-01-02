package com.trade.securities.adapter.in.web;

import com.trade.securities.application.port.in.LoadStockDataUseCase;
import com.trade.securities.domain.StockData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stock-data")
@RequiredArgsConstructor
public class StockDataController {

    private final LoadStockDataUseCase loadStockDataUseCase;

    @GetMapping
    public List<StockData> getStockData(
            @RequestParam String date,
            @RequestParam(defaultValue = "KOSDAQ") String type) {
        return loadStockDataUseCase.getStockData(date, type);
    }
}
