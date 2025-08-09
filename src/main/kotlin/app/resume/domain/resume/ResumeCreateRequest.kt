package app.resume.domain.resume

data class ResumeCreateRequest(
    val title: String,
    val name: String,
    val email: String,
    val callingCode: String,
    val nationalNumber: String,
)
