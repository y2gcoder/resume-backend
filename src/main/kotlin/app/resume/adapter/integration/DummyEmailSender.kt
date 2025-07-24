package app.resume.adapter.integration

import app.resume.application.member.required.EmailSender
import app.resume.domain.shared.Email
import org.springframework.context.annotation.Fallback
import org.springframework.stereotype.Component

@Component
@Fallback
class DummyEmailSender : EmailSender {
    override fun send(email: Email, subject: String, body: String) {
        println("DummyEmailSender send email: $email")
    }
}