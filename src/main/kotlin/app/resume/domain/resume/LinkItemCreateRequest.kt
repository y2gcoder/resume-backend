package app.resume.domain.resume

data class LinkItemCreateRequest(
    val url: String,
    val description: String? = null,
)
