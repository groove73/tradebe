package com.trade.securities.application.port.in;

import com.trade.securities.domain.CommodityTradingInfo;
import java.util.List;

public interface GetCommodityTradingInfoUseCase {
    List<CommodityTradingInfo> getCommodityTradingInfo(String marketType, String basDd);
}
