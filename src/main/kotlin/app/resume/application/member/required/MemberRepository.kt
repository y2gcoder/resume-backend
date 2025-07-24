package app.resume.application.member.required

import app.resume.domain.member.Member
import app.resume.domain.shared.Email
import org.springframework.data.repository.Repository

interface MemberRepository : Repository<Member, Long> {
    fun save(member: Member): Member

    fun findByEmail(email: Email): Member?

    fun findById(memberId: Long): Member?
}