package app.resume.application.member

import app.resume.application.member.provided.MemberFinder
import app.resume.application.member.provided.MemberRegister
import app.resume.application.member.required.EmailSender
import app.resume.application.member.required.MemberRepository
import app.resume.domain.member.DuplicateEmailException
import app.resume.domain.member.Member
import app.resume.domain.member.MemberRegisterRequest
import app.resume.domain.member.PasswordEncoder
import app.resume.domain.shared.Email
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated

@Service
@Transactional
@Validated
class MemberModifyService(
    private val memberFinder: MemberFinder,
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
        sendWelcomeEmail(member)

        return member
    }

    override fun activate(memberId: Long): Member {
        val member = memberFinder.find(memberId)

        member.activate()

        return memberRepository.save(member)
    }

    private fun sendWelcomeEmail(member: Member) {
        emailSender.send(member.email, "등록을 완료해주세요", "아래 링크를 클릭해서 등록을 완료해주세요")
    }

    private fun checkDuplicateEmail(registerRequest: MemberRegisterRequest) {
        memberRepository.findByEmail(Email(registerRequest.email))?.let {
            throw DuplicateEmailException("이미 사용 중인 이메일 입니다: ${registerRequest.email}")
        }
    }
}