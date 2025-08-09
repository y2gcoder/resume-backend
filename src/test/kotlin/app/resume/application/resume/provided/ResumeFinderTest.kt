package app.resume.application.resume.provided

import app.resume.ResumeApplicationTestConfiguration
import app.resume.application.member.provided.MemberRegister
import app.resume.domain.member.MemberFixture
import app.resume.domain.resume.ResumeFixture.createResumeCreateRequest
import jakarta.persistence.EntityManager
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
@Import(ResumeApplicationTestConfiguration::class)
class ResumeFinderTest(
    val resumeFinder: ResumeFinder,
    val memberRegister: MemberRegister,
    val resumeCreator: ResumeCreator,
    val entityManager: EntityManager,
) {
    @Test
    fun find() {
        val writer = memberRegister.register(MemberFixture.createMemberRegisterRequest())
        memberRegister.activate(writer.id)
        val resume = resumeCreator.create(writer.id, createResumeCreateRequest())
        entityManager.flush()
        entityManager.clear()

        val found = resumeFinder.find(resume.id)

        Assertions.assertThat(found.id).isEqualTo(resume.id)
    }
}
