package com.trade.securities.application.port.out;

import com.trade.securities.domain.BondTradingInfo;
import java.util.List;

public interface LoadBondTradingInfoPort {
    List<BondTradingInfo> loadBondTradingInfo(String marketType, String basDd);
}
