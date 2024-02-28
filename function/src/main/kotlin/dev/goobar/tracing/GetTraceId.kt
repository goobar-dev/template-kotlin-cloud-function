package dev.goobar.tracing

private val cloudTraceRegEx = "^projects/([a-zA-Z0-9_-]+)/traces/([a-fA-F0-9]+)\$".toRegex()

fun String?.getTraceId(): TraceId? =
    this?.let {
        val matchResult = cloudTraceRegEx.matchEntire(it)
        val id = matchResult?.groups?.get(2)?.value
        if (id == null) {
            null
        } else {
            TraceId(id)
        }
    }
