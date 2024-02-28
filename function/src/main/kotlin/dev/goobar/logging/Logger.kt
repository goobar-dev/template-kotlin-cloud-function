package dev.goobar.logging

interface Logger {
    fun error(msg: String, vararg properties: Pair<String, Any>?)

    fun warn(msg: String, vararg properties: Pair<String, Any>?)

    fun info(msg: String, vararg properties: Pair<String, Any>?)

    fun debug(msg: String, vararg properties: Pair<String, Any>?)
}
