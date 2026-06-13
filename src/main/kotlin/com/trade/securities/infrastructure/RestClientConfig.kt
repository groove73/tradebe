package com.trade.securities.infrastructure

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.core.UriBuilder
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration
import org.slf4j.LoggerFactory

@ApplicationScoped
class RestClient(
    private val objectMapper: ObjectMapper
) {
    private val httpClient: HttpClient = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(10))
        .build()

    fun get(): RequestHeadersSpec {
        return RequestBuilder("GET", httpClient, objectMapper)
    }

    fun post(): RequestBodySpec {
        return RequestBuilder("POST", httpClient, objectMapper)
    }

    interface RequestHeadersSpec {
        fun uri(uri: String): RequestHeadersSpec
        fun header(headerName: String, headerValue: String): RequestHeadersSpec
        fun retrieve(): ResponseSpec
    }

    interface RequestBodySpec : RequestHeadersSpec {
        fun body(body: Any): RequestBodySpec
        override fun uri(uri: String): RequestBodySpec
        override fun header(headerName: String, headerValue: String): RequestBodySpec
    }

    interface ResponseSpec {
        fun <T> body(type: Class<T>): T?
    }

    private class RequestBuilder(
        private val method: String,
        private val httpClient: HttpClient,
        private val objectMapper: ObjectMapper
    ) : RequestBodySpec {
        private var uri: String? = null
        private val headers = mutableListOf<Pair<String, String>>()
        private var body: Any? = null

        override fun uri(uri: String): RequestBuilder {
            this.uri = uri
            return this
        }

        override fun header(headerName: String, headerValue: String): RequestBuilder {
            headers.add(headerName to headerValue)
            return this
        }

        override fun body(body: Any): RequestBuilder {
            this.body = body
            return this
        }

        override fun retrieve(): ResponseSpec {
            return ResponseSpecImpl(method, uri!!, headers, body, httpClient, objectMapper)
        }
    }

    private class ResponseSpecImpl(
        private val method: String,
        private val uri: String,
        private val headers: List<Pair<String, String>>,
        private val body: Any?,
        private val httpClient: HttpClient,
        private val objectMapper: ObjectMapper
    ) : ResponseSpec {
        private val log = LoggerFactory.getLogger(javaClass)

        override fun <T> body(type: Class<T>): T? {
            val requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(uri))

            headers.forEach { (name, value) ->
                requestBuilder.header(name, value)
            }

            val bodyPublisher = if (body != null) {
                val json = objectMapper.writeValueAsString(body)
                requestBuilder.header("Content-Type", "application/json")
                HttpRequest.BodyPublishers.ofString(json)
            } else {
                HttpRequest.BodyPublishers.noBody()
            }

            requestBuilder.method(method, bodyPublisher)

            val request = requestBuilder.build()
            val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())

            if (response.statusCode() in 200..299) {
                return if (type == String::class.java) {
                    response.body() as T
                } else {
                    objectMapper.readValue(response.body(), type)
                }
            } else {
                log.error("HTTP request failed with status: {}, body: {}", response.statusCode(), response.body())
                throw RuntimeException("HTTP request failed with status: ${response.statusCode()}")
            }
        }
    }
}

class UriComponentsBuilder private constructor(private val builder: UriBuilder) {
    companion object {
        fun fromUriString(uri: String): UriComponentsBuilder {
            return UriComponentsBuilder(UriBuilder.fromUri(uri))
        }
    }

    fun queryParam(name: String, vararg values: Any?): UriComponentsBuilder {
        if (values.isNotEmpty() && values[0] != null) {
            val valStr = values[0].toString()
            if (valStr.isNotEmpty()) {
                builder.queryParam(name, valStr)
            }
        }
        return this
    }

    fun build(): UriComponentsBuilder {
        return this
    }

    fun toUriString(): String {
        return builder.build().toString()
    }
}
