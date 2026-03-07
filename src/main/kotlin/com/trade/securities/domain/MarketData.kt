package com.trade.securities.domain

data class MarketData(
    val basDt: String? = null, // Base Date
    val idxNm: String? = null, // Index Name
    val clpr: String? = null, // Closing Price
    val fltRt: String? = null  // Fluctuation Rate
)
