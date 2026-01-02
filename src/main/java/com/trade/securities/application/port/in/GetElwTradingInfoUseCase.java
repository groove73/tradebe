package com.trade.securities.application.port.in;

import com.trade.securities.domain.ElwTradingInfo;
import java.util.List;

public interface GetElwTradingInfoUseCase {
    List<ElwTradingInfo> getElwTradingInfo(String basDd);
}
