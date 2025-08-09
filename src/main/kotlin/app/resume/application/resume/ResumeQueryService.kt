package app.resume.application.resume

import app.resume.application.resume.provided.ResumeFinder
import app.resume.application.resume.required.ResumeRepository
import app.resume.domain.resume.Resume
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ResumeQueryService(
    private val resumeRepository: ResumeRepository,
) : ResumeFinder {
    override fun find(resumeId: Long): Resume {
        return resumeRepository.findById(resumeId)
            ?: throw IllegalArgumentException("이력서를 찾을 수 없습니다 id: $resumeId")
    }

}