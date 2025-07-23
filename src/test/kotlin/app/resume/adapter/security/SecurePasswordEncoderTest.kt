package app.resume.adapter.security

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SecurePasswordEncoderTest {
    @Test
    fun securePasswordEncoder() {
        val securePasswordEncoder = SecurePasswordEncoder()

        val passwordHash = securePasswordEncoder.encode("secret")

        assertThat(securePasswordEncoder.matches("secret", passwordHash)).isTrue()
        assertThat(securePasswordEncoder.matches("wrong", passwordHash)).isFalse()
    }
}
