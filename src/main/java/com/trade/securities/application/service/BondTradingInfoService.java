package com.trade.securities.application.service;

import com.trade.securities.application.port.in.GetBondTradingInfoUseCase;
import com.trade.securities.application.port.out.LoadBondTradingInfoPort;
import com.trade.securities.domain.BondTradingInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BondTradingInfoService implements GetBondTradingInfoUseCase {

    private final LoadBondTradingInfoPort loadBondTradingInfoPort;

    @Override
    public List<BondTradingInfo> getBondTradingInfo(String marketType, String basDd) {
        return loadBondTradingInfoPort.loadBondTradingInfo(marketType, basDd);
    }
}
