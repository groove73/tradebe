package com.trade.securities.application.port.out;

import java.util.Map;

public interface LoadFscStockSubscriptionRightPort {
    Map<String, Object> loadSubscriptionRights(int pageNo, int numOfRows, String basDt, String itmsNm,
            String likeItmsNm);
}
