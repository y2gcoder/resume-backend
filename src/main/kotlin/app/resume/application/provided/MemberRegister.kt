package app.resume.application.provided

import app.resume.domain.Member
import app.resume.domain.MemberRegisterRequest
import jakarta.validation.Valid

interface MemberRegister {
    fun register(@Valid registerRequest: MemberRegisterRequest): Member
}