package app.resume.domain.resume

import app.resume.domain.member.Member

data class ResumeCreateRequest(
    val writer: Member,
    val title: String,
    val name: String,
    val email: String,
    val callingCode: String,
    val nationalNumber: String,
)
