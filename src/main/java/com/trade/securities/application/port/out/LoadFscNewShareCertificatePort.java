package com.trade.securities.application.port.out;

import java.util.Map;

public interface LoadFscNewShareCertificatePort {
    Map<String, Object> loadNewShareCertificates(int pageNo, int numOfRows, String basDt, String itmsNm,
            String likeItmsNm);
}
