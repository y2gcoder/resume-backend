package app.resume.application.resume.provided

import app.resume.domain.resume.Resume
import app.resume.domain.resume.ResumeCreateRequest

/**
 * 이력서 작성과 관련된 기능을 제공한다
 */
interface ResumeCreator {
    fun create(writerId: Long, createRequest: ResumeCreateRequest): Resume
}