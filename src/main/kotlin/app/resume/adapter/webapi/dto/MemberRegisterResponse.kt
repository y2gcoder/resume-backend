package app.resume.adapter.webapi.dto

import app.resume.domain.member.Member

data class MemberRegisterResponse(val memberId: Long, val email: String) {
    companion object {
        fun of(member: Member): MemberRegisterResponse {
            return MemberRegisterResponse(member.id, member.email.address)
        }
    }
}
