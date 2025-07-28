package app.resume.domain.resume

import app.resume.domain.member.Member
import java.time.YearMonth

object ResumeFixture {
    fun createResumeCreateRequest(writer: Member): ResumeCreateRequest = ResumeCreateRequest(
        writer,
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
}