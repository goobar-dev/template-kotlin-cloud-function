package dev.goobar

import com.google.cloud.functions.HttpRequest
import dev.goobar.logging.Logger
import dev.goobar.logging.buildTraceAttribute
import dev.goobar.tracing.ProjectId
import dev.goobar.tracing.TraceId
import dev.goobar.tracing.getTraceId
import kotlin.jvm.optionals.getOrNull

fun HttpRequest.getLogger(name: String): Logger = HttpRequestLogger(name, this)

/**
 * Attempts to parse a Google Cloud trace identifier in the format of projects/<gcp project id>/traces/<trace id> ex:
 * projects/dev/traces/bde90770a9af03d682d893e33004844d
 */
internal val HttpRequest?.cloudTraceId: TraceId?
    get() =
        when (this) {
            null -> null
            else -> {
                getFirstHeader("x-cloud-trace-context").getOrNull().getTraceId()
            }
        }

internal fun HttpRequest.buildTraceAttribute(projectId: ProjectId = ProjectId()): Pair<String, String>? =
    cloudTraceId.buildTraceAttribute()
