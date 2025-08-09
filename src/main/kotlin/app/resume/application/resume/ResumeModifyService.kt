package app.resume.application.resume

import app.resume.application.member.provided.MemberFinder
import app.resume.application.resume.provided.ResumeCreator
import app.resume.application.resume.required.ResumeRepository
import app.resume.domain.resume.Resume
import app.resume.domain.resume.ResumeCreateRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated

@Service
@Validated
@Transactional
class ResumeModifyService(
    private val memberFinder: MemberFinder,
    private val resumeRepository: ResumeRepository,
) : ResumeCreator {

    override fun create(
        writerId: Long,
        createRequest: ResumeCreateRequest
    ): Resume {
        val writer = memberFinder.find(writerId)

        val resume = Resume.create(writer, createRequest)

        return resumeRepository.save(resume)
    }
}