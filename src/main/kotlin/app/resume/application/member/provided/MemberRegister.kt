package app.resume.application.member.provided

import app.resume.domain.member.Member
import app.resume.domain.member.MemberRegisterRequest
import jakarta.validation.Valid

/**
 * 회원의 등록과 관련된 기능을 제공한다
 */
interface MemberRegister {
    fun register(@Valid registerRequest: MemberRegisterRequest): Member

    fun activate(memberId: Long): Member

    fun deactivate(memberId: Long): Member
}