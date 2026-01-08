package com.trade.securities.application.port.out;

import java.util.Map;

public interface LoadFscStockPricePort {
    Map<String, Object> loadStockPrices(int pageNo, int numOfRows, String basDt, String itmsNm, String likeItmsNm);
}
