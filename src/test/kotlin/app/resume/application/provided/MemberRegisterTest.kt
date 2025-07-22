package app.resume.application.provided

import app.resume.ResumeApplicationTestConfiguration
import app.resume.domain.DuplicateEmailException
import app.resume.domain.MemberFixture
import app.resume.domain.MemberRegisterRequest
import app.resume.domain.MemberStatus
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
        var member = memberRegister.register(MemberFixture.createMemberRegisterRequest())
        entityManager.flush()
        entityManager.clear()

        member = memberRegister.activate(member.id)
        entityManager.flush()

        assertThat(member.status).isEqualTo(MemberStatus.ACTIVE)
    }

    @Test
    fun memberRegisterRequestFail() {
        extracted(MemberRegisterRequest("y2gcoder@gmail.com", "G", "longsecret"))
        extracted(MemberRegisterRequest("y2gcoder@gmail.com", "Geun______________________", "longsecret"))
        extracted(MemberRegisterRequest("y2gcodergmail.com", "y2gcoder", "longsecret"))
    }

    private fun extracted(invalid: MemberRegisterRequest) {
        assertThatThrownBy {
            memberRegister.register(invalid)
        }.isInstanceOf(ConstraintViolationException::class.java)
    }


}