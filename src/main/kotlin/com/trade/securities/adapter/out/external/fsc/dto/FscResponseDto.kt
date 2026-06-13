package com.trade.securities.adapter.out.external.fsc.dto

data class FscResponse<T>(
    val response: ResponseContent<T>? = null
)

data class ResponseContent<T>(
    val header: FscHeader? = null,
    val body: FscBody<T>? = null
)

data class FscHeader(
    val resultCode: String? = null,
    val resultMsg: String? = null
)

data class FscBody<T>(
    val numOfRows: Int = 0,
    val pageNo: Int = 0,
    val totalCount: Int = 0,
    val items: FscItems<T>? = null
)

data class FscItems<T>(
    val item: List<T> = emptyList()
)
