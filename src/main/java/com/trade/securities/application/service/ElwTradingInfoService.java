package com.trade.securities.application.service;

import com.trade.securities.application.port.in.GetElwTradingInfoUseCase;
import com.trade.securities.application.port.out.LoadElwTradingInfoPort;
import com.trade.securities.domain.ElwTradingInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ElwTradingInfoService implements GetElwTradingInfoUseCase {

    private final LoadElwTradingInfoPort loadElwTradingInfoPort;

    @Override
    public List<ElwTradingInfo> getElwTradingInfo(String basDd) {
        return loadElwTradingInfoPort.loadElwTradingInfo(basDd);
    }
}
