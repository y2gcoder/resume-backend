package app.resume.domain

class Member private constructor(
    val email: String,
    var nickname: String,
    var passwordHash: String,
    var status: MemberStatus = MemberStatus.PENDING,
) {
    companion object {
        fun register(
            email: String,
            nickname: String,
            password: String,
            passwordEncoder: PasswordEncoder,
        ): Member {
            return Member(
                email = email,
                nickname = nickname,
                passwordHash = passwordEncoder.encode(password),
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
}