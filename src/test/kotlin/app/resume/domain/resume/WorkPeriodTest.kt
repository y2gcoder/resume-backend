package app.resume.domain.resume

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.YearMonth

class WorkPeriodTest : StringSpec({

    "재직기간 생성" {
        val workPeriod = Period(
            startedAt = YearMonth.of(2024, 7),
            endedAt = YearMonth.of(2024, 7),
        )

        workPeriod.startedAt shouldBe YearMonth.of(2024, 7)
        workPeriod.endedAt shouldBe YearMonth.of(2024, 7)
        workPeriod.isOngoing() shouldBe false
    }

    "재직기간 생성 - 진행중" {
        val workPeriod = Period(
            startedAt = YearMonth.of(2025, 7),
        )

        workPeriod.startedAt shouldBe YearMonth.of(2025, 7)
        workPeriod.isOngoing() shouldBe true
    }

    "재직기간 생성 실패 - 시작일시가 종료일시보다 미래" {
        shouldThrow<IllegalArgumentException> {
            Period(
                startedAt = YearMonth.of(2025, 7),
                endedAt = YearMonth.of(2025, 6),
            )
        }
    }
})
