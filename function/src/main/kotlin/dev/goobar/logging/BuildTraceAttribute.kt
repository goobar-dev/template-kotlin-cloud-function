package dev.goobar.logging

import dev.goobar.tracing.ProjectId
import dev.goobar.tracing.TraceId

fun TraceId?.buildTraceAttribute(projectId: ProjectId = ProjectId()): Pair<String, String>? =
    when (this) {
        null -> null
        else -> {
            "logging.googleapis.com/trace" to String.format("projects/%s/traces/%s", projectId.id, id)
        }
    }
