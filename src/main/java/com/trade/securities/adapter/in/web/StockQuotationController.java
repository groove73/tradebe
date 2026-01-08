package com.trade.securities.adapter.in.web;

import com.trade.securities.application.port.in.GetFscBeneficiaryCertificateUseCase;
import com.trade.securities.application.port.in.GetFscNewShareCertificateUseCase;
import com.trade.securities.application.port.in.GetFscStockPriceUseCase;
import com.trade.securities.application.port.in.GetFscStockSubscriptionRightUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/stock-quotation")
@RequiredArgsConstructor
public class StockQuotationController {

    private final GetFscStockPriceUseCase getFscStockPriceUseCase;
    private final GetFscNewShareCertificateUseCase getFscNewShareCertificateUseCase;
    private final GetFscBeneficiaryCertificateUseCase getFscBeneficiaryCertificateUseCase;
    private final GetFscStockSubscriptionRightUseCase getFscStockSubscriptionRightUseCase;

    @GetMapping("/price")
    public Map<String, Object> getStockPrices(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int numOfRows,
            @RequestParam String basDt,
            @RequestParam(required = false) String itmsNm,
            @RequestParam(required = false) String likeItmsNm) {
        return getFscStockPriceUseCase.getStockPrices(pageNo, numOfRows, basDt, itmsNm, likeItmsNm);
    }

    @GetMapping("/new-share")
    public Map<String, Object> getNewShareCertificates(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int numOfRows,
            @RequestParam String basDt,
            @RequestParam(required = false) String itmsNm,
            @RequestParam(required = false) String likeItmsNm) {
        return getFscNewShareCertificateUseCase.getNewShareCertificates(pageNo, numOfRows, basDt, itmsNm, likeItmsNm);
    }

    @GetMapping("/beneficiary")
    public Map<String, Object> getBeneficiaryCertificates(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int numOfRows,
            @RequestParam String basDt,
            @RequestParam(required = false) String itmsNm,
            @RequestParam(required = false) String likeItmsNm) {
        return getFscBeneficiaryCertificateUseCase.getBeneficiaryCertificates(pageNo, numOfRows, basDt, itmsNm,
                likeItmsNm);
    }

    @GetMapping("/subscription-right")
    public Map<String, Object> getSubscriptionRights(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int numOfRows,
            @RequestParam String basDt,
            @RequestParam(required = false) String itmsNm,
            @RequestParam(required = false) String likeItmsNm) {
        return getFscStockSubscriptionRightUseCase.getSubscriptionRights(pageNo, numOfRows, basDt, itmsNm, likeItmsNm);
    }
}
