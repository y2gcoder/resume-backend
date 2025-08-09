package app.resume.application.resume

import app.resume.application.member.provided.MemberFinder
import app.resume.application.resume.provided.ResumeFinder
import app.resume.application.resume.provided.ResumeWriter
import app.resume.application.resume.required.ResumeRepository
import app.resume.domain.resume.Resume
import app.resume.domain.resume.ResumeCreateRequest
import app.resume.domain.resume.WorkExperienceCreateRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated

@Service
@Validated
@Transactional
class ResumeModifyService(
    private val memberFinder: MemberFinder,
    private val resumeFinder: ResumeFinder,
    private val resumeRepository: ResumeRepository,
) : ResumeWriter {

    override fun create(
        writerId: Long,
        createRequest: ResumeCreateRequest
    ): Resume {
        val writer = memberFinder.find(writerId)

        val resume = Resume.create(writer, createRequest)

        return resumeRepository.save(resume)
    }

    override fun addWorkExperience(
        resumeId: Long,
        createWorkExperienceRequest: WorkExperienceCreateRequest
    ): Resume {
        val resume = resumeFinder.find(resumeId)

        resume.addWorkExperience(createWorkExperienceRequest)

        return resumeRepository.save(resume)
    }
}