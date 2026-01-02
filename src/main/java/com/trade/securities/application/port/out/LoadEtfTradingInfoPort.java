package com.trade.securities.application.port.out;

import com.trade.securities.domain.EtfTradingInfo;
import java.util.List;

public interface LoadEtfTradingInfoPort {
    List<EtfTradingInfo> loadEtfTradingInfo(String basDd);
}
