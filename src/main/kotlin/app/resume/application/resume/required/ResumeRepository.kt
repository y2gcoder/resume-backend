package app.resume.application.resume.required

import app.resume.domain.resume.Resume
import org.springframework.data.repository.Repository

interface ResumeRepository : Repository<Resume, Long> {
    fun save(resume: Resume): Resume

    fun findById(resumeId: Long): Resume?
}