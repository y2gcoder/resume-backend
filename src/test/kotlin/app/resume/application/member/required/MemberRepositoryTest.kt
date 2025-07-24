package app.resume.application.member.required

import app.resume.domain.member.Member
import app.resume.domain.member.MemberFixture.createMemberRegisterRequest
import app.resume.domain.member.MemberFixture.createPasswordEncoder
import jakarta.persistence.EntityManager
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.dao.DataIntegrityViolationException

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    lateinit var memberRepository: MemberRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun registerMember() {
        val member = Member.register(createMemberRegisterRequest(), createPasswordEncoder())

        assertThat(member.id).isEqualTo(0L)

        memberRepository.save(member)

        assertThat(member.id).isNotEqualTo(0L)

        entityManager.flush()
    }

    @Test
    fun duplicateEmailFail() {
        val member = Member.register(createMemberRegisterRequest(), createPasswordEncoder())
        memberRepository.save(member)

        val member2 = Member.register(createMemberRegisterRequest(), createPasswordEncoder())
        assertThatThrownBy { memberRepository.save(member2) }
            .isInstanceOf(DataIntegrityViolationException::class.java)
    }
}