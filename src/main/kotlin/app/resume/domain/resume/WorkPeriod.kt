package app.resume.domain.resume

import java.time.YearMonth

data class WorkPeriod(
    val startedAt: YearMonth,
    val endedAt: YearMonth? = null,
) {
    init {
        require(endedAt == null || !startedAt.isAfter(endedAt))
    }

    fun isOngoing(): Boolean = endedAt == null
}
