package com.trade.securities.adapter.in.web;

import com.trade.securities.application.port.in.GetDerivativesTradingInfoUseCase;
import com.trade.securities.domain.DerivativesTradingInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/derivatives")
@RequiredArgsConstructor
public class DerivativesTradingInfoController {

    private final GetDerivativesTradingInfoUseCase getDerivativesTradingInfoUseCase;

    @GetMapping("/trading-info/{marketType}")
    public List<DerivativesTradingInfo> getDerivativesTradingInfo(
            @PathVariable String marketType,
            @RequestParam String basDd) {
        return getDerivativesTradingInfoUseCase.getDerivativesTradingInfo(marketType, basDd);
    }
}
