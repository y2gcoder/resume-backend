package app.resume.domain

data class ResumeCreateRequest(
    val writer: Member,
    val title: String,
    val name: String,
    val email: String,
    val callingCode: String,
    val nationalNumber: String,
)
