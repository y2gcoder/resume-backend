package app.resume.domain.resume

import jakarta.persistence.Embeddable
import java.time.Instant

@Embeddable
data class AttachmentItem(
    val fileKey: String,
    val filename: String,
    val contentType: String,
    val fileSize: Long,
    val uploadedAt: Instant,
) {
    companion object {
        fun create(createRequest: AttachmentItemCreateRequest): AttachmentItem {
            return AttachmentItem(
                createRequest.fileKey,
                createRequest.filename,
                createRequest.contentType,
                createRequest.fileSize,
                createRequest.uploadedAt,
            )
        }
    }
}
