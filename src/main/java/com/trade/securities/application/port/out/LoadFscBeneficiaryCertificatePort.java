package com.trade.securities.application.port.out;

import java.util.Map;

public interface LoadFscBeneficiaryCertificatePort {
    Map<String, Object> loadBeneficiaryCertificates(int pageNo, int numOfRows, String basDt, String itmsNm,
            String likeItmsNm);
}
