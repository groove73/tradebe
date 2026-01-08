package com.trade.securities.application.service;

import com.trade.securities.application.port.in.GetFscStockPriceUseCase;
import com.trade.securities.application.port.out.LoadFscStockPricePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class FscStockPriceService implements GetFscStockPriceUseCase {

    private final LoadFscStockPricePort loadFscStockPricePort;

    @Override
    public Map<String, Object> getStockPrices(int pageNo, int numOfRows, String basDt, String itmsNm,
            String likeItmsNm) {
        return loadFscStockPricePort.loadStockPrices(pageNo, numOfRows, basDt, itmsNm, likeItmsNm);
    }
}
