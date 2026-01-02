package com.trade.securities.adapter.in.web;

import com.trade.securities.application.port.in.GetEtnTradingInfoUseCase;
import com.trade.securities.domain.EtnTradingInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/etn")
@RequiredArgsConstructor
public class EtnTradingInfoController {

    private final GetEtnTradingInfoUseCase getEtnTradingInfoUseCase;

    @GetMapping("/trading-info")
    public List<EtnTradingInfo> getEtnTradingInfo(@RequestParam String basDd) {
        return getEtnTradingInfoUseCase.getEtnTradingInfo(basDd);
    }
}
