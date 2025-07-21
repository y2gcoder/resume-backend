package app.resume.domain

import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.NaturalId

@Entity
class Member private constructor(
    @Embedded
    @NaturalId
    var email: Email,

    var nickname: String,

    var passwordHash: String,

    @Enumerated(EnumType.STRING)
    var status: MemberStatus = MemberStatus.PENDING,
) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

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