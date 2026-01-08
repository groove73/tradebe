package com.trade.securities.application.port.in;

import java.util.Map;

public interface GetFscNewShareCertificateUseCase {
    Map<String, Object> getNewShareCertificates(int pageNo, int numOfRows, String basDt, String itmsNm,
            String likeItmsNm);
}
