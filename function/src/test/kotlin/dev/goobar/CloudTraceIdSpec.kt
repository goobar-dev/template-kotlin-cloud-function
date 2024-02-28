package dev.goobar

import com.google.cloud.functions.HttpRequest
import dev.goobar.tracing.TraceId
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import java.io.BufferedReader
import java.io.InputStream
import java.util.*

class CloudTraceIdSpec :
    ShouldSpec({
        context("HTTPRequest") {
            should("have null traceId when no headers are present") { getTestHttpRequest().cloudTraceId shouldBe null }

            should("have null traceId when x-cloud-trace-context header is missing") {
                getTestHttpRequest(headers = mutableMapOf("Content-Type" to mutableListOf("*/*"))).cloudTraceId shouldBe null
            }

            should("have null traceId when x-cloud-trace-context header has unexpected format") {
                getTestHttpRequest(
                        headers =
                            mutableMapOf("x-cloud-trace-context" to mutableListOf("traces/bde90770a9af03d682d893e33004844d"))
                    )
                    .cloudTraceId shouldBe null
            }

            should("have traceId bde90770a9af03d682d893e33004844d") {
                getTestHttpRequest(
                        headers =
                            mutableMapOf(
                                "x-cloud-trace-context" to
                                    mutableListOf("projects/dev/traces/bde90770a9af03d682d893e33004844d")
                            )
                    )
                    .cloudTraceId shouldBe TraceId("bde90770a9af03d682d893e33004844d")
            }
        }
    })

private fun getTestHttpRequest(headers: MutableMap<String, MutableList<String>> = mutableMapOf()): HttpRequest =
    object : HttpRequest {
        override fun getContentType(): Optional<String> = TODO("Not yet implemented")

        override fun getContentLength(): Long = TODO("Not yet implemented")

        override fun getCharacterEncoding(): Optional<String> = TODO("Not yet implemented")

        override fun getInputStream(): InputStream = TODO("Not yet implemented")

        override fun getReader(): BufferedReader = TODO("Not yet implemented")

        override fun getHeaders(): MutableMap<String, MutableList<String>> = headers

        override fun getMethod(): String = TODO("Not yet implemented")

        override fun getUri(): String = TODO("Not yet implemented")

        override fun getPath(): String = TODO("Not yet implemented")

        override fun getQuery(): Optional<String> = TODO("Not yet implemented")

        override fun getQueryParameters(): MutableMap<String, MutableList<String>> = TODO("Not yet implemented")

        override fun getParts(): MutableMap<String, HttpRequest.HttpPart> = TODO("Not yet implemented")
    }
