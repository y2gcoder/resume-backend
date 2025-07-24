package app.resume.domain.member

import app.resume.domain.AbstractEntity
import app.resume.domain.shared.Email
import jakarta.persistence.CascadeType
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToOne
import org.hibernate.annotations.NaturalId
import java.time.Instant

@Entity
class Member(

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

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var detail: MemberDetail
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
                detail = MemberDetail.create(),
            )
        }
    }

    fun activate(activatedAt: Instant = Instant.now()) {
        check(status == MemberStatus.PENDING) { "PENDING 상태가 아닙니다" }

        this.status = MemberStatus.ACTIVE
        this.detail.activate(activatedAt)
    }

    fun deactivate(deactivatedAt: Instant = Instant.now()) {
        check(status == MemberStatus.ACTIVE) { "ACTIVE 상태가 아닙니다" }

        this.status = MemberStatus.DEACTIVATED
        this.detail.deactivate(deactivatedAt)
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