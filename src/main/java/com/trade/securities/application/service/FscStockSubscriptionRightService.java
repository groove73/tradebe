package com.trade.securities.application.service;

import com.trade.securities.application.port.in.GetFscStockSubscriptionRightUseCase;
import com.trade.securities.application.port.out.LoadFscStockSubscriptionRightPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class FscStockSubscriptionRightService implements GetFscStockSubscriptionRightUseCase {

    private final LoadFscStockSubscriptionRightPort loadFscStockSubscriptionRightPort;

    @Override
    public Map<String, Object> getSubscriptionRights(int pageNo, int numOfRows, String basDt, String itmsNm,
            String likeItmsNm) {
        return loadFscStockSubscriptionRightPort.loadSubscriptionRights(pageNo, numOfRows, basDt, itmsNm, likeItmsNm);
    }
}
