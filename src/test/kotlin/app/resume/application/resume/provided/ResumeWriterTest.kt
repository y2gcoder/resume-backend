package app.resume.application.resume.provided

import app.resume.ResumeApplicationTestConfiguration
import app.resume.application.member.provided.MemberRegister
import app.resume.domain.member.Member
import app.resume.domain.member.MemberFixture
import app.resume.domain.resume.ResumeFixture.createResumeCreateRequest
import app.resume.domain.resume.ResumeFixture.createWorkExperienceRequest
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest
@Transactional
@Import(ResumeApplicationTestConfiguration::class)
class ResumeWriterTest(
    val memberRegister: MemberRegister,
    val resumeWriter: ResumeWriter,
    val entityManager: EntityManager,
) {

    @Test
    fun create() {
        val writer = createWriter()

        val createRequest = createResumeCreateRequest()

        val resume = resumeWriter.create(writer.id, createRequest)

        Assertions.assertThat(resume.id).isNotNull()
        Assertions.assertThat(resume.writer).isEqualTo(writer)
    }

    @Test
    fun addWorkExperience() {
        val writer = createWriter()

        var resume = resumeWriter.create(writer.id, createResumeCreateRequest())
        entityManager.flush()
        entityManager.clear()

        resume = resumeWriter.addWorkExperience(resume.id, createWorkExperienceRequest())
        entityManager.flush()

        Assertions.assertThat(resume.workExperiences).hasSize(1)
    }

    private fun createWriter(): Member {
        var writer = memberRegister.register(MemberFixture.createMemberRegisterRequest())
        writer = memberRegister.activate(writer.id)
        entityManager.flush()
        entityManager.clear()

        return writer
    }
}
