package app.resume.application.member.provided

import app.resume.domain.member.Member

/**
 * 회원을 조회한다
 */
interface MemberFinder {
    fun find(memberId: Long): Member
}