package app.resume

import app.resume.application.required.EmailSender
import app.resume.domain.Email
import app.resume.domain.MemberFixture
import app.resume.domain.PasswordEncoder
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class ResumeApplicationTestConfiguration {

    @Bean
    fun emailSender(): EmailSender {
        return object: EmailSender {
            override fun send(
                email: Email,
                subject: String,
                body: String
            ) {
                println("Sending email: $email")
            }
        }
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = MemberFixture.createPasswordEncoder()
}