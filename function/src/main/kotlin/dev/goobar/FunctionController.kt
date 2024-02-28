package dev.goobar

import com.google.cloud.functions.HttpRequest
import com.google.cloud.functions.HttpResponse
import dev.goobar.logging.Logger
import dev.goobar.logging.StructuredLogger

class FunctionController(private val logger: Logger = StructuredLogger(FunctionController::class.simpleName!!)) {

    fun doWork(request: HttpRequest, response: HttpResponse): HttpResponse {
        logger.debug("Debug log without explicitly adding the trace id")
        logger.debug(msg = "Debug log with extra properties", properties = arrayOf("userId" to "12345"))
        logger.info("Info log without explicitly adding the trace id")

        return response.apply { setStatusCode(200, getCompletionMessage()) }
    }

    internal fun getCompletionMessage() = "Function Complete"
}
