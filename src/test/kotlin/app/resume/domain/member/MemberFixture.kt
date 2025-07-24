package app.resume.domain.member

object MemberFixture {
    fun createMemberRegisterRequest(): MemberRegisterRequest {
        return MemberRegisterRequest(
            "y2gcoder@gmail.com",
            "문파관작",
            "bestpassword",
        )
    }

    fun createPasswordEncoder(): PasswordEncoder {
        return object : PasswordEncoder {
            override fun encode(password: String): String = password.uppercase()
            override fun matches(password: String, passwordHash: String): Boolean = encode(password) == passwordHash
        }
    }

    fun createMember(): Member {
        return Member.register(createMemberRegisterRequest(), createPasswordEncoder())
    }
}