package app.resume.domain.resume

import app.resume.domain.member.Member

object ResumeFixture {
    fun createResumeCreateRequest(writer: Member): ResumeCreateRequest = ResumeCreateRequest(
        writer,
        "title",
        "문파관작",
        "y2gcoder@gmail.com",
        "82",
        "1012341234",
    )
}