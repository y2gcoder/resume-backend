package app.resume.adapter.security

import app.resume.domain.PasswordEncoder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class SecurePasswordEncoder(
    private val bCryptPasswordEncoder: BCryptPasswordEncoder = BCryptPasswordEncoder()
) : PasswordEncoder {
    override fun encode(password: String): String {
        return bCryptPasswordEncoder.encode(password)
    }

    override fun matches(password: String, passwordHash: String): Boolean {
        return bCryptPasswordEncoder.matches(password, passwordHash)
    }
}