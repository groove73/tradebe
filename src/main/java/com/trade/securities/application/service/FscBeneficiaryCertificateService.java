package com.trade.securities.application.service;

import com.trade.securities.application.port.in.GetFscBeneficiaryCertificateUseCase;
import com.trade.securities.application.port.out.LoadFscBeneficiaryCertificatePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class FscBeneficiaryCertificateService implements GetFscBeneficiaryCertificateUseCase {

    private final LoadFscBeneficiaryCertificatePort loadFscBeneficiaryCertificatePort;

    @Override
    public Map<String, Object> getBeneficiaryCertificates(int pageNo, int numOfRows, String basDt, String itmsNm,
            String likeItmsNm) {
        return loadFscBeneficiaryCertificatePort.loadBeneficiaryCertificates(pageNo, numOfRows, basDt, itmsNm,
                likeItmsNm);
    }
}
