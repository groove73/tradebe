package com.trade.securities.application.port.in;

import java.util.Map;

public interface GetFscStockSubscriptionRightUseCase {
    Map<String, Object> getSubscriptionRights(int pageNo, int numOfRows, String basDt, String itmsNm,
            String likeItmsNm);
}
