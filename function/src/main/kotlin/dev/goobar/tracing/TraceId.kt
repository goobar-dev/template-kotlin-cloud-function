package dev.goobar.tracing

@JvmInline
value class TraceId private constructor(val id: String) {
    companion object {
        operator fun invoke(id: String): TraceId {
            require(id.isNotBlank()) { "TraceId must not be blank" }
            return TraceId(id)
        }
    }
}
