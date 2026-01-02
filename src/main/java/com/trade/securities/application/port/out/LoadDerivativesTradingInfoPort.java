package com.trade.securities.application.port.out;

import com.trade.securities.domain.DerivativesTradingInfo;
import java.util.List;

public interface LoadDerivativesTradingInfoPort {
    List<DerivativesTradingInfo> loadDerivativesTradingInfo(String marketType, String basDd);
}
