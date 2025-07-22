package app.resume.application.provided

import app.resume.domain.Member

/**
 * 회원을 조회한다
 */
interface MemberFinder {
    fun find(memberId: Long): Member
}