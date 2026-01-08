package com.trade.securities.application.port.in;

import java.util.Map;

public interface GetFscStockPriceUseCase {
    Map<String, Object> getStockPrices(int pageNo, int numOfRows, String basDt, String itmsNm, String likeItmsNm);
}
