package com.trade.securities.application.port.in;

import com.trade.securities.domain.EtnTradingInfo;
import java.util.List;

public interface GetEtnTradingInfoUseCase {
    List<EtnTradingInfo> getEtnTradingInfo(String basDd);
}
