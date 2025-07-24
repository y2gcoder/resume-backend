package app.resume.application.member.required

import app.resume.domain.shared.Email

interface EmailSender {
    fun send(email: Email, subject: String, body: String)
}