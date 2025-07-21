package app.resume.adapter.integration

import app.resume.application.required.EmailSender
import app.resume.domain.Email
import org.springframework.stereotype.Component

@Component
class DummyEmailSender : EmailSender {
    override fun send(email: Email, subject: String, body: String) {
        println("DummyEmailSender send email: $email, subject: $subject, body: $body")
    }
}