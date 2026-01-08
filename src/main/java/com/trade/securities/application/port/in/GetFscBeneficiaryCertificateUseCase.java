package com.trade.securities.application.port.in;

import java.util.Map;

public interface GetFscBeneficiaryCertificateUseCase {
    Map<String, Object> getBeneficiaryCertificates(int pageNo, int numOfRows, String basDt, String itmsNm,
            String likeItmsNm);
}
