package app.resume.application.provided

import app.resume.ResumeApplicationTestConfiguration
import app.resume.domain.DuplicateEmailException
import app.resume.domain.MemberFixture
import app.resume.domain.MemberStatus
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
@Import(ResumeApplicationTestConfiguration::class)
class MemberRegisterTest(
    @Autowired private val memberRegister: MemberRegister,
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
}