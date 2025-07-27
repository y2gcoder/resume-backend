package app.resume.domain.resume

import java.time.YearMonth

data class WorkExperienceCreateRequest(
    val companyName: String,
    val startedAt: YearMonth,
    val endedAt: YearMonth? = null,
    val employmentType: EmploymentType,
    val role: String,
    val position: String,
    val achievement: String? = null,
)
