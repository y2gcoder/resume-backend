package app.resume.application

import app.resume.application.provided.MemberFinder
import app.resume.application.required.MemberRepository
import app.resume.domain.Member
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated

@Service
@Transactional
@Validated
class MemberQueryService(
    private val memberRepository: MemberRepository,
) : MemberFinder {
    override fun find(memberId: Long): Member {
        return memberRepository.findById(memberId)
            ?: throw IllegalArgumentException("회원을 찾을 수 없습니다 id: $memberId")
    }
}