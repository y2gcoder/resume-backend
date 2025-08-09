package app.resume.application.resume.provided

import app.resume.domain.resume.Resume

/**
 * 이력서를 조회한다
 */
interface ResumeFinder {
    fun find(resumeId: Long): Resume
}