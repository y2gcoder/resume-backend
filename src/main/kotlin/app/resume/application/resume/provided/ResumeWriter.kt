package app.resume.application.resume.provided

import app.resume.domain.resume.Resume
import app.resume.domain.resume.ResumeCreateRequest
import app.resume.domain.resume.WorkExperienceCreateRequest
import jakarta.validation.Valid

/**
 * 이력서 작성과 관련된 기능을 제공한다
 */
interface ResumeWriter {
    fun create(writerId: Long, @Valid createRequest: ResumeCreateRequest): Resume

    fun addWorkExperience(resumeId: Long, @Valid createWorkExperienceRequest: WorkExperienceCreateRequest): Resume
}