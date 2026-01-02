package com.trade.securities.application.port.out;

import com.trade.securities.domain.ElwTradingInfo;
import java.util.List;

public interface LoadElwTradingInfoPort {
    List<ElwTradingInfo> loadElwTradingInfo(String basDd);
}
