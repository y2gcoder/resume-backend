package app.resume.adapter.webapi

import app.resume.adapter.webapi.dto.MemberRegisterResponse
import app.resume.application.member.provided.MemberRegister
import app.resume.domain.member.MemberRegisterRequest
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberApi(
    private val memberRegister: MemberRegister,
) {

    @PostMapping("/api/members")
    fun register(
        @RequestBody @Valid request: MemberRegisterRequest,
    ): MemberRegisterResponse {
        val member = memberRegister.register(request)

        return MemberRegisterResponse.of(member)
    }
}