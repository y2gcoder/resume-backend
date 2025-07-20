package app.resume.domain

import app.resume.domain.MemberFixture.createMemberRegisterRequest
import app.resume.domain.MemberFixture.createPasswordEncoder
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MemberTest : StringSpec({
    lateinit var passwordEncoder: PasswordEncoder
    lateinit var member: Member

    beforeEach {
        passwordEncoder = createPasswordEncoder()
        member = Member.register(
            createMemberRegisterRequest(),
            passwordEncoder
        )
    }

    "회원 등록" {
        member.status shouldBe MemberStatus.PENDING
    }

    "등록 완료" {
        member.activate()

        member.status shouldBe MemberStatus.ACTIVE
    }

    "등록 완료 실패 - PENDING 상태가 아님" {
        member.activate()

        shouldThrow<IllegalStateException> { member.activate() }
    }

    "탈퇴" {
        member.activate()

        member.deactivate()

        member.status shouldBe MemberStatus.DEACTIVATED
    }

    "탈퇴 실패" {
        shouldThrow<IllegalStateException> { member.deactivate() }

        member.activate()
        member.deactivate()

        shouldThrow<IllegalStateException> { member.deactivate() }
    }

    "비밀번호 검증" {
        member.verifyPassword("bestpassword", passwordEncoder) shouldBe true
        member.verifyPassword("wrong", passwordEncoder) shouldBe false
    }

    "닉네임 변경" {
        member.nickname shouldBe "문파관작"

        member.changeNickname("작관파문")

        member.nickname shouldBe "작관파문"
    }

    "비밀번호 변경" {
        member.changePassword("verybestpassword", passwordEncoder)

        member.verifyPassword("verybestpassword", passwordEncoder) shouldBe true
    }

    "등록 완료 상태 체크" {
        member.isActive() shouldBe false

        member.activate()

        member.isActive() shouldBe true

        member.deactivate()

        member.isActive() shouldBe false
    }
})
