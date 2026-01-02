package com.trade.securities.application.port.out;

import com.trade.securities.domain.EtnTradingInfo;
import java.util.List;

public interface LoadEtnTradingInfoPort {
    List<EtnTradingInfo> loadEtnTradingInfo(String basDd);
}
