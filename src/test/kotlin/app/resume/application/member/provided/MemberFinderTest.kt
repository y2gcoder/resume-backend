package app.resume.application.member.provided

import app.resume.ResumeApplicationTestConfiguration
import app.resume.domain.member.MemberFixture
import jakarta.persistence.EntityManager
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
@Import(ResumeApplicationTestConfiguration::class)
class MemberFinderTest(
    val memberFinder: MemberFinder,
    val memberRegister: MemberRegister,
    val entityManager: EntityManager,
) {
    @Test
    fun find() {
        val member = memberRegister.register(MemberFixture.createMemberRegisterRequest())
        entityManager.flush()
        entityManager.clear()

        val found = memberFinder.find(member.id)

        assertThat(member.id).isEqualTo(found.id)
    }

    @Test
    fun findFail() {
        assertThatThrownBy { memberFinder.find(999L) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}