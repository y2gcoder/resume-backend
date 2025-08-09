package app.resume.domain.resume

import jakarta.persistence.Embeddable

@Embeddable
data class LinkItem(
    val url: String,
    val description: String? = null,
) {
    companion object {
        fun create(createRequest: LinkItemCreateRequest): LinkItem {
            return LinkItem(
                createRequest.url,
                createRequest.description
            )
        }
    }
}

