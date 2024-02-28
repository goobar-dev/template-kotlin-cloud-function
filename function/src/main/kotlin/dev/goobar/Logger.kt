package dev.goobar

import com.google.cloud.functions.HttpRequest
import net.logstash.logback.argument.StructuredArguments
import org.slf4j.LoggerFactory

interface Logger {
    fun error(msg: String, vararg properties: Pair<String, Any>?)

    fun warn(msg: String, vararg properties: Pair<String, Any>?)

    fun info(msg: String, vararg properties: Pair<String, Any>?)

    fun debug(msg: String, vararg properties: Pair<String, Any>?)
}

class StructuredLogger(name: String) : Logger {

    private val logger = LoggerFactory.getLogger(name)

    private fun Pair<String, Any>.toStructuredValues() = let { (key, value) -> StructuredArguments.kv(key, value) }

    override fun error(msg: String, vararg properties: Pair<String, Any>?) {
        val svs = properties.filterNotNull().map { it.toStructuredValues() }.toTypedArray()
        logger.error(msg, *svs)
    }

    override fun warn(msg: String, vararg properties: Pair<String, Any>?) {
        val svs = properties.filterNotNull().map { it.toStructuredValues() }.toTypedArray()
        logger.warn(msg, *svs)
    }

    override fun info(msg: String, vararg properties: Pair<String, Any>?) {
        val svs = properties.filterNotNull().map { it.toStructuredValues() }.toTypedArray()
        logger.info(msg, *svs)
    }

    override fun debug(msg: String, vararg properties: Pair<String, Any>?) {
        val svs = properties.filterNotNull().map { it.toStructuredValues() }.toTypedArray()
        logger.debug(msg, *svs)
    }
}

fun HttpRequest.getLogger(name: String): Logger = HttpRequestLogger(name, this)

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
