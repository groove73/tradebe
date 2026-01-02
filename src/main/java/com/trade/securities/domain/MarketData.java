package com.trade.securities.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketData {
    private String basDt; // Base Date
    private String idxNm; // Index Name
    private String clpr; // Closing Price
    private String fltRt; // Fluctuation Rate
    // Add more fields as needed based on API response
}
