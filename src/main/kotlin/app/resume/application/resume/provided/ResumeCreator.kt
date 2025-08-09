package app.resume.application.resume.provided

import app.resume.domain.resume.Resume
import app.resume.domain.resume.ResumeCreateRequest

interface ResumeCreator {
    fun create(writerId: Long, createRequest: ResumeCreateRequest): Resume
}