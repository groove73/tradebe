package com.trade.securities.adapter.in.web;

import com.trade.securities.application.port.in.GetCommodityTradingInfoUseCase;
import com.trade.securities.domain.CommodityTradingInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commodities")
@RequiredArgsConstructor
public class CommodityTradingInfoController {

    private final GetCommodityTradingInfoUseCase getCommodityTradingInfoUseCase;

    @GetMapping("/trading-info/{marketType}")
    public List<CommodityTradingInfo> getCommodityTradingInfo(
            @PathVariable String marketType,
            @RequestParam String basDd) {
        return getCommodityTradingInfoUseCase.getCommodityTradingInfo(marketType, basDd);
    }
}
