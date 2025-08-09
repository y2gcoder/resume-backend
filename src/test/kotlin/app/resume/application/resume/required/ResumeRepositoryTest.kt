package app.resume.application.resume.required

import app.resume.application.member.required.MemberRepository
import app.resume.domain.member.MemberFixture.createMember
import app.resume.domain.resume.Resume
import app.resume.domain.resume.ResumeFixture.createResumeCreateRequest
import app.resume.domain.shared.PhoneNumber
import jakarta.persistence.EntityManager
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class ResumeRepositoryTest {

    @Autowired
    lateinit var resumeRepository: ResumeRepository

    @Autowired
    lateinit var memberRepository: MemberRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun createResume() {
        val writer = createMember()
        writer.activate()
        memberRepository.save(writer)
        
        val resume = Resume.create(writer, createResumeCreateRequest())

        assertThat(resume.id).isEqualTo(0L)

        resumeRepository.save(resume)

        assertThat(resume.id).isNotEqualTo(0L)
        assertThat(resume.title).isEqualTo("title")
        assertThat(resume.name).isEqualTo("문파관작")
        assertThat(resume.writer).isEqualTo(writer)
        assertThat(resume.phoneNumber).isEqualTo(PhoneNumber("82", "1012341234"))
        assertThat(resume.createdAt).isNotNull()

        entityManager.flush()
        entityManager.clear()

        val found: Resume = resumeRepository.findById(resume.id) ?: throw NoSuchElementException()
        assertThat(found.createdAt).isNotNull()
    }
}