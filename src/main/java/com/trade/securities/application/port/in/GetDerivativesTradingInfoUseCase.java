package com.trade.securities.application.port.in;

import com.trade.securities.domain.DerivativesTradingInfo;
import java.util.List;

public interface GetDerivativesTradingInfoUseCase {
    List<DerivativesTradingInfo> getDerivativesTradingInfo(String marketType, String basDd);
}
