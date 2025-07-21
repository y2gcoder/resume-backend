package app.resume.application.required

import app.resume.domain.Member
import org.springframework.data.repository.Repository

interface MemberRepository : Repository<Member, Long> {
    fun save(member: Member): Member
}