package app.resume.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PhoneNumberTest : StringSpec({

    "동등성 비교" {
        val phoneNumber1 = PhoneNumber("82", "1012341234")
        val phoneNumber2 = PhoneNumber("82", "1012341234")

        phoneNumber1 shouldBe phoneNumber2
    }

    "국제 전화 식별 코드 체크" {
        val nationalNumber = "1012341234"

        listOf(
            "",
            "123456",
            "-"
        ).forEach {
            shouldThrow<IllegalArgumentException> {
                PhoneNumber(it, nationalNumber)
            }
        }
    }

    "국내 번호 체크" {
        val callingCode = "82"

        listOf(
            "",
            "1234567890123456",
            "10-1234-1234"
        ).forEach {
            shouldThrow<IllegalArgumentException> {
                PhoneNumber(callingCode, it)
            }
        }
    }
})
