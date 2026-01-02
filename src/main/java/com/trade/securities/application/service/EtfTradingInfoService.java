package com.trade.securities.application.service;

import com.trade.securities.application.port.in.GetEtfTradingInfoUseCase;
import com.trade.securities.application.port.out.LoadEtfTradingInfoPort;
import com.trade.securities.domain.EtfTradingInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EtfTradingInfoService implements GetEtfTradingInfoUseCase {

    private final LoadEtfTradingInfoPort loadEtfTradingInfoPort;

    @Override
    public List<EtfTradingInfo> getEtfTradingInfo(String basDd) {
        return loadEtfTradingInfoPort.loadEtfTradingInfo(basDd);
    }
}
