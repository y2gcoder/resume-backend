package app.resume.application.provided

import app.resume.domain.Member
import app.resume.domain.MemberRegisterRequest
import jakarta.validation.Valid

/**
 * 회원의 등록과 관련된 기능을 제공한다
 */
interface MemberRegister {
    fun register(@Valid registerRequest: MemberRegisterRequest): Member

    fun activate(memberId: Long): Member
}