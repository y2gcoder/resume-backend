package app.resume.domain.resume

import java.time.Instant

data class AttachmentItemCreateRequest(
    val fileKey: String,
    val filename: String,
    val contentType: String,
    val fileSize: Long,
    val uploadedAt: Instant,
)
