package dev.goobar

import com.google.cloud.functions.HttpFunction
import com.google.cloud.functions.HttpRequest
import com.google.cloud.functions.HttpResponse

class CloudFunction : HttpFunction {

    override fun service(request: HttpRequest, response: HttpResponse) {
        val logger = request.getLogger(CloudFunction::class.simpleName!!)
        FunctionController(logger).doWork(request, response)
    }
}
