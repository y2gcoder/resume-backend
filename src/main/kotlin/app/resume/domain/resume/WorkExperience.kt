package app.resume.domain.resume

import app.resume.domain.AbstractEntity
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity

@Entity
class WorkExperience private constructor(
    /** 회사명 **/
    @Column(nullable = false, length = 100)
    var companyName: String,

    /** 재직기간 **/
    @Embedded
    var period: Period,

    /** 근무 형태 **/
    var employmentType: EmploymentType,

    /** 직무 **/
    var role: String,

    /** 직책 **/
    var position: String,

    /** 주요 성과 **/
    var achievement: String?,
) : AbstractEntity() {
    companion object {
        fun create(
            createRequest: WorkExperienceCreateRequest
        ): WorkExperience {
            require(createRequest.companyName.isNotBlank())
            require(createRequest.role.isNotBlank())
            require(createRequest.position.isNotBlank())

            return WorkExperience(
                createRequest.companyName,
                Period(
                    createRequest.startedAt,
                    createRequest.endedAt,
                ),
                createRequest.employmentType,
                createRequest.role,
                createRequest.position,
                createRequest.achievement
            )
        }
    }

    fun isCurrentJob(): Boolean = period.isOngoing()
}