package com.trade.securities.adapter.in.web;

import com.trade.securities.application.port.in.GetBondTradingInfoUseCase;
import com.trade.securities.domain.BondTradingInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bond")
@RequiredArgsConstructor
public class BondTradingInfoController {

    private final GetBondTradingInfoUseCase getBondTradingInfoUseCase;

    @GetMapping("/trading-info/{marketType}")
    public List<BondTradingInfo> getBondTradingInfo(
            @PathVariable String marketType,
            @RequestParam String basDd) {
        return getBondTradingInfoUseCase.getBondTradingInfo(marketType, basDd);
    }
}
