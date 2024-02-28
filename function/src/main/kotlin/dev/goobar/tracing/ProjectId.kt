package dev.goobar.tracing

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
