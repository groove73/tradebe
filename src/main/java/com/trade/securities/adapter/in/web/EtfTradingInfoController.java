package com.trade.securities.adapter.in.web;

import com.trade.securities.application.port.in.GetEtfTradingInfoUseCase;
import com.trade.securities.domain.EtfTradingInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/etf")
@RequiredArgsConstructor
public class EtfTradingInfoController {

    private final GetEtfTradingInfoUseCase getEtfTradingInfoUseCase;

    @GetMapping("/trading-info")
    public List<EtfTradingInfo> getEtfTradingInfo(@RequestParam String basDd) {
        return getEtfTradingInfoUseCase.getEtfTradingInfo(basDd);
    }
}
