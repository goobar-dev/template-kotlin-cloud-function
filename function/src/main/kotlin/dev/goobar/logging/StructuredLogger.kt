package dev.goobar.logging

import net.logstash.logback.argument.StructuredArguments
import org.slf4j.LoggerFactory

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
