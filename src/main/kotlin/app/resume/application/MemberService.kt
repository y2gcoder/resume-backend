package app.resume.application

import app.resume.application.provided.MemberRegister
import app.resume.application.required.EmailSender
import app.resume.application.required.MemberRepository
import app.resume.domain.DuplicateEmailException
import app.resume.domain.Email
import app.resume.domain.Member
import app.resume.domain.MemberRegisterRequest
import app.resume.domain.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated

@Service
@Transactional
@Validated
class MemberService(
    private val memberRepository: MemberRepository,
    private val emailSender: EmailSender,
    private val passwordEncoder: PasswordEncoder,
) : MemberRegister {
    override fun register(registerRequest: MemberRegisterRequest): Member {
        // check
        checkDuplicateEmail(registerRequest)

        // domain model
        val member = Member.register(registerRequest, passwordEncoder)

        // repository
        memberRepository.save(member)

        // post process
        emailSender.send(member.email, "등록을 완료해주세요", "아래 링크를 클릭해서 등록을 완료해주세요")

        return member
    }

    private fun checkDuplicateEmail(registerRequest: MemberRegisterRequest) {
        memberRepository.findByEmail(Email(registerRequest.email))?.let {
            throw DuplicateEmailException("이미 사용 중인 이메일 입니다: ${registerRequest.email}")
        }
    }
}