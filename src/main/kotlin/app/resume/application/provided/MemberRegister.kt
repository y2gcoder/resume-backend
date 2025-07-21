package app.resume.application.provided

import app.resume.domain.Member
import app.resume.domain.MemberRegisterRequest

interface MemberRegister {
    fun register(registerRequest: MemberRegisterRequest): Member
}