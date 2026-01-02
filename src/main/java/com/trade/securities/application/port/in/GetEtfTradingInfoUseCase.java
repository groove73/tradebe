package com.trade.securities.application.port.in;

import com.trade.securities.domain.EtfTradingInfo;
import java.util.List;

public interface GetEtfTradingInfoUseCase {
    List<EtfTradingInfo> getEtfTradingInfo(String basDd);
}
