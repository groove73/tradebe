package com.trade.securities.application.port.out;

import com.trade.securities.domain.CommodityTradingInfo;
import java.util.List;

public interface LoadCommodityTradingInfoPort {
    List<CommodityTradingInfo> loadCommodityTradingInfo(String marketType, String basDd);
}
