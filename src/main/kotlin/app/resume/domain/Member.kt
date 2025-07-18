package app.resume.domain

class Member private constructor(
    val email: Email,
    var nickname: String,
    var passwordHash: String,
    var status: MemberStatus = MemberStatus.PENDING,
) {
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