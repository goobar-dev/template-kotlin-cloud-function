package dev.goobar

import com.google.cloud.functions.HttpRequest
import kotlin.jvm.optionals.getOrNull

internal val HttpRequest?.cloudTraceId: String?
    get() =
        when (this) {
            null -> null
            else -> {
                getFirstHeader("x-cloud-trace-context")
                    .map { traceHeader -> traceHeader.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0] }
                    .getOrNull()
            }
        }

@JvmInline
value class ProjectId private constructor(val id: String) {
    companion object {
        operator fun invoke(): ProjectId {
            val projectId = System.getenv("GCP_PROJECT_ID")
            require(projectId.isNullOrBlank().not()) { "GCP_PROJECT_ID env variable is missing or empty" }
            return ProjectId(projectId)
        }

        operator fun invoke(id: String): ProjectId {
            require(id.isBlank().not()) { "GCP_PROJECT_ID env variable is missing or empty" }
            return ProjectId(id)
        }
    }
}

internal fun HttpRequest.buildTraceAttribute(projectId: ProjectId = ProjectId()): Pair<String, String>? =
    when (cloudTraceId) {
        null -> null
        else -> {
            "logging.googleapis.com/trace" to String.format("projects/%s/traces/%s", projectId.id, cloudTraceId)
        }
    }
