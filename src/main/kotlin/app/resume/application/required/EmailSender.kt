package app.resume.application.required

import app.resume.domain.Email

interface EmailSender {
    fun send(email: Email, subject: String, body: String)
}