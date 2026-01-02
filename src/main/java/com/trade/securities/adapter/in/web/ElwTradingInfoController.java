package com.trade.securities.adapter.in.web;

import com.trade.securities.application.port.in.GetElwTradingInfoUseCase;
import com.trade.securities.domain.ElwTradingInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/elw")
@RequiredArgsConstructor
public class ElwTradingInfoController {

    private final GetElwTradingInfoUseCase getElwTradingInfoUseCase;

    @GetMapping("/trading-info")
    public List<ElwTradingInfo> getElwTradingInfo(@RequestParam String basDd) {
        return getElwTradingInfoUseCase.getElwTradingInfo(basDd);
    }
}
