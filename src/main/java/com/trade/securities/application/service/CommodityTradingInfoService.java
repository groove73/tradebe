package com.trade.securities.application.service;

import com.trade.securities.application.port.in.GetCommodityTradingInfoUseCase;
import com.trade.securities.application.port.out.LoadCommodityTradingInfoPort;
import com.trade.securities.domain.CommodityTradingInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommodityTradingInfoService implements GetCommodityTradingInfoUseCase {

    private final LoadCommodityTradingInfoPort client;

    @Override
    public List<CommodityTradingInfo> getCommodityTradingInfo(String marketType, String basDd) {
        return client.loadCommodityTradingInfo(marketType, basDd);
    }
}
