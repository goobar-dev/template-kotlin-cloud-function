package dev.goobar

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class FunctionalControllerSpec :
    StringSpec({
        "getCompletionMessage() should be Function Complete" {
            FunctionController().getCompletionMessage() shouldBe "Function Complete"
        }
    })
