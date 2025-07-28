package app.resume.domain.resume

import jakarta.persistence.Embeddable
import java.time.YearMonth

@Embeddable
data class WorkPeriod(
    val startedAt: YearMonth,
    val endedAt: YearMonth? = null,
) {
    init {
        require(endedAt == null || !startedAt.isAfter(endedAt))
    }

    fun isOngoing(): Boolean = endedAt == null
}
