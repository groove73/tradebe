package com.trade.securities.application.service;

import com.trade.securities.application.port.in.GetFscNewShareCertificateUseCase;
import com.trade.securities.application.port.out.LoadFscNewShareCertificatePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class FscNewShareCertificateService implements GetFscNewShareCertificateUseCase {

    private final LoadFscNewShareCertificatePort loadFscNewShareCertificatePort;

    @Override
    public Map<String, Object> getNewShareCertificates(int pageNo, int numOfRows, String basDt, String itmsNm,
            String likeItmsNm) {
        return loadFscNewShareCertificatePort.loadNewShareCertificates(pageNo, numOfRows, basDt, itmsNm, likeItmsNm);
    }
}
