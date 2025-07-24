package app.resume.domain.shared

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class EmailTest : StringSpec({
    "동등성 테스트" {
        val email1 = Email("y2gcoder@gmail.com")
        val email2 = Email("y2gcoder@gmail.com")

        email1 shouldBe email2
    }

    "이메일 주소 유효성 체크 " {
        shouldThrow<IllegalArgumentException> {
            Email("invalid-email")
        }

        Email("y2gcoder@gmail.com")
    }
})