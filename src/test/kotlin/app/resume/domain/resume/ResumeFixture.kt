package app.resume.domain.resume

import java.time.Instant
import java.time.YearMonth

object ResumeFixture {
    fun createResumeCreateRequest(): ResumeCreateRequest = ResumeCreateRequest(
        "title",
        "문파관작",
        "y2gcoder@gmail.com",
        "82",
        "1012341234",
    )

    fun createWorkExperienceRequest(): WorkExperienceCreateRequest = WorkExperienceCreateRequest(
        "레쥬미",
        YearMonth.of(2025, 7),
        null,
        EmploymentType.FULL_TIME,
        "백엔드 엔지니어",
        "개발자",
        "레쥬미 백엔드 개발함"
    )

    fun createWorkExperienceRequest(startedAt: YearMonth, endedAt: YearMonth): WorkExperienceCreateRequest = WorkExperienceCreateRequest(
        "레쥬미",
        startedAt,
        endedAt,
        EmploymentType.FULL_TIME,
        "백엔드 엔지니어",
        "개발자",
        "레쥬미 백엔드 개발함"
    )

    fun createPortfolioCreateRequest(): PortfolioCreateRequest = PortfolioCreateRequest(
        links = createLinkItemCreateRequests(),
        attachments = createAttachmentItemRequests(),
    )

    fun createAttachmentItemRequests(): List<AttachmentItemCreateRequest> = listOf(
        AttachmentItemCreateRequest(
            "fileKey",
            "my-project",
            "application/pdf",
            2048,
            Instant.now(),
        ),
    )

    fun createLinkItemCreateRequests(): List<LinkItemCreateRequest> = listOf(
        LinkItemCreateRequest(
            "https://github.com/y2gcoder",
            "깃헙"
        ),
        LinkItemCreateRequest(
            "https://resume.me",
            "레쥬미"
        )
    )
}