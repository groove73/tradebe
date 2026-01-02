package com.trade.securities.application.service;

import com.trade.securities.application.port.in.GetDerivativesTradingInfoUseCase;
import com.trade.securities.application.port.out.LoadDerivativesTradingInfoPort;
import com.trade.securities.domain.DerivativesTradingInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DerivativesTradingInfoService implements GetDerivativesTradingInfoUseCase {

    private final LoadDerivativesTradingInfoPort loadDerivativesTradingInfoPort;

    @Override
    public List<DerivativesTradingInfo> getDerivativesTradingInfo(String marketType, String basDd) {
        return loadDerivativesTradingInfoPort.loadDerivativesTradingInfo(marketType, basDd);
    }
}
