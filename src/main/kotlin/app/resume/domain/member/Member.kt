package app.resume.domain.member

import app.resume.domain.AbstractEntity
import app.resume.domain.shared.Email
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import org.hibernate.annotations.NaturalId

@Entity
class Member private constructor(

    /** 이메일 **/
    @NaturalId
    @Embedded
    var email: Email,

    /** 닉네임 **/
    var nickname: String,

    /** 해시된 비밀번호 **/
    var passwordHash: String,

    /** 회원 상태 **/
    var status: MemberStatus = MemberStatus.PENDING,
) : AbstractEntity() {

    companion object {
        fun register(
            registerRequest: MemberRegisterRequest,
            passwordEncoder: PasswordEncoder,
        ): Member {
            return Member(
                email = Email(registerRequest.email),
                nickname = registerRequest.nickname,
                passwordHash = passwordEncoder.encode(registerRequest.password),
            )
        }
    }

    fun activate() {
        check(status == MemberStatus.PENDING) { "PENDING 상태가 아닙니다" }

        this.status = MemberStatus.ACTIVE
    }

    fun deactivate() {
        check(status == MemberStatus.ACTIVE) { "ACTIVE 상태가 아닙니다" }

        this.status = MemberStatus.DEACTIVATED
    }

    fun verifyPassword(password: String, passwordEncoder: PasswordEncoder): Boolean {
        return passwordEncoder.matches(password, passwordHash)
    }

    fun changeNickname(nickname: String) {
        this.nickname = nickname
    }

    fun changePassword(password: String, passwordEncoder: PasswordEncoder) {
        this.passwordHash = passwordEncoder.encode(password)
    }

    fun isActive() = status == MemberStatus.ACTIVE
}