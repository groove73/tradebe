package com.trade.securities.application.port.in;

import com.trade.securities.domain.BondTradingInfo;
import java.util.List;

public interface GetBondTradingInfoUseCase {
    List<BondTradingInfo> getBondTradingInfo(String marketType, String basDd);
}
