package app.resume.domain.resume

import jakarta.validation.constraints.NotBlank
import java.time.YearMonth

data class WorkExperienceCreateRequest(
    @field:NotBlank val companyName: String,
    val startedAt: YearMonth,
    val endedAt: YearMonth? = null,
    val employmentType: EmploymentType,
    @field:NotBlank val role: String,
    @field:NotBlank val position: String,
    val achievement: String? = null,
)
