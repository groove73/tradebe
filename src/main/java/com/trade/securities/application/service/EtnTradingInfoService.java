package com.trade.securities.application.service;

import com.trade.securities.application.port.in.GetEtnTradingInfoUseCase;
import com.trade.securities.application.port.out.LoadEtnTradingInfoPort;
import com.trade.securities.domain.EtnTradingInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EtnTradingInfoService implements GetEtnTradingInfoUseCase {

    private final LoadEtnTradingInfoPort loadEtnTradingInfoPort;

    @Override
    public List<EtnTradingInfo> getEtnTradingInfo(String basDd) {
        return loadEtnTradingInfoPort.loadEtnTradingInfo(basDd);
    }
}
