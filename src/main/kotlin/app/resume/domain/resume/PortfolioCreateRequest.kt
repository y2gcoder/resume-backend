package app.resume.domain.resume

data class PortfolioCreateRequest(
    val links: List<LinkItemCreateRequest> = emptyList(),
    val attachments: List<AttachmentItemCreateRequest> = emptyList(),
)
