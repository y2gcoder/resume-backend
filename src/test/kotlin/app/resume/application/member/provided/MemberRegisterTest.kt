package app.resume.application.member.provided

import app.resume.ResumeApplicationTestConfiguration
import app.resume.domain.member.DuplicateEmailException
import app.resume.domain.member.Member
import app.resume.domain.member.MemberFixture
import app.resume.domain.member.MemberRegisterRequest
import app.resume.domain.member.MemberStatus
import jakarta.persistence.EntityManager
import jakarta.validation.ConstraintViolationException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
@Import(ResumeApplicationTestConfiguration::class)
class MemberRegisterTest(
    val memberRegister: MemberRegister,
    val entityManager: EntityManager,
) {
    @Test
    fun register() {
        val member = memberRegister.register(MemberFixture.createMemberRegisterRequest())

        assertThat(member.id).isNotNull()
        assertThat(member.status).isEqualTo(MemberStatus.PENDING)
    }

    @Test
    fun duplicateEmailFail() {
        memberRegister.register(MemberFixture.createMemberRegisterRequest())

        assertThatThrownBy {
            memberRegister.register(MemberFixture.createMemberRegisterRequest())
        }
            .isInstanceOf(DuplicateEmailException::class.java)
    }

    @Test
    fun activate() {
        var member = registerMember()

        member = memberRegister.activate(member.id)
        entityManager.flush()

        assertThat(member.status).isEqualTo(MemberStatus.ACTIVE)
        assertThat(member.detail.activatedAt).isNotNull()
    }

    @Test
    fun deactivate() {
        var member = registerMember()

        memberRegister.activate(member.id)
        entityManager.flush()
        entityManager.clear()

        member = memberRegister.deactivate(member.id)

        assertThat(member.status).isEqualTo(MemberStatus.DEACTIVATED)
        assertThat(member.detail.deactivatedAt).isNotNull()
    }

    private fun registerMember(): Member {
        val member = memberRegister.register(MemberFixture.createMemberRegisterRequest())
        entityManager.flush()
        entityManager.clear()
        return member
    }

    @Test
    fun memberRegisterRequestFail() {
        checkValidation(MemberRegisterRequest("y2gcoder@gmail.com", "G", "longsecret"))
        checkValidation(MemberRegisterRequest("y2gcoder@gmail.com", "Geun______________________", "longsecret"))
        checkValidation(MemberRegisterRequest("y2gcodergmail.com", "y2gcoder", "longsecret"))
    }

    private fun checkValidation(invalid: MemberRegisterRequest) {
        assertThatThrownBy {
            memberRegister.register(invalid)
        }.isInstanceOf(ConstraintViolationException::class.java)
    }


}