package app.resume.domain.resume

import app.resume.domain.AbstractEntity
import jakarta.persistence.CollectionTable
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn

@Entity
class Portfolio private constructor(
    @ElementCollection
    @CollectionTable(name = "portfolio_links", joinColumns = [JoinColumn(name = "portfolio_id")])
    val links: MutableList<LinkItem>,
    @ElementCollection
    @CollectionTable(name = "portfolio_attachments", joinColumns = [JoinColumn(name = "portfolio_id")])
    val attachments: MutableList<AttachmentItem>,
) : AbstractEntity() {
    companion object {
        fun create(
            createRequest: PortfolioCreateRequest,
        ): Portfolio {
            return Portfolio(
                createRequest.links.map { LinkItem.create(it) }.toMutableList(),
                createRequest.attachments.map { AttachmentItem.create(it) }.toMutableList(),
            )
        }
    }

    fun replaceLinks(linkItemCreateRequests: List<LinkItemCreateRequest>) {
        this.links.clear()
        this.links.addAll(linkItemCreateRequests.map { LinkItem.create(it) }.toMutableList())
    }

    fun replaceAttachments(attachmentItemCreateRequests: List<AttachmentItemCreateRequest>) {
        this.attachments.clear()
        this.attachments.addAll(attachmentItemCreateRequests.map { AttachmentItem.create(it) }.toMutableList())
    }
}