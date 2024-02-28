package dev.goobar

import com.google.cloud.functions.HttpRequest
import dev.goobar.logging.Logger
import dev.goobar.logging.StructuredLogger

class HttpRequestLogger(name: String, private val request: HttpRequest) : Logger {

    private val logger: StructuredLogger = StructuredLogger(name)

    override fun error(msg: String, vararg properties: Pair<String, Any>?) =
        logger.error(msg, request.buildTraceAttribute(), *properties)

    override fun warn(msg: String, vararg properties: Pair<String, Any>?) =
        logger.warn(msg, request.buildTraceAttribute(), *properties)

    override fun info(msg: String, vararg properties: Pair<String, Any>?) =
        logger.info(msg, request.buildTraceAttribute(), *properties)

    override fun debug(msg: String, vararg properties: Pair<String, Any>?) =
        logger.debug(msg, request.buildTraceAttribute(), *properties)
}
