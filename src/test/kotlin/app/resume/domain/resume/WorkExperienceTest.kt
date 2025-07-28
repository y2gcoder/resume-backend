package app.resume.domain.resume

import app.resume.domain.resume.ResumeFixture.createWorkExperienceRequest
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.YearMonth

class WorkExperienceTest : StringSpec({
    val workExperience = WorkExperience.create(
        createWorkExperienceRequest(),
    )

    "이력서 생성" {
        workExperience.companyName shouldBe "레쥬미"
        workExperience.workPeriod.startedAt shouldBe YearMonth.of(2025, 7)
        workExperience.workPeriod.endedAt shouldBe null
        workExperience.employmentType shouldBe EmploymentType.FULL_TIME
        workExperience.role shouldBe "백엔드 엔지니어"
        workExperience.position shouldBe "개발자"
        workExperience.achievement shouldBe "레쥬미 백엔드 개발함"
    }

    "재직중인지 체크" {
        workExperience.isCurrentJob() shouldBe true
    }

    "재직기간 중 시작일시는 종료일시와 같거나 이전이어야 한다" {
        shouldThrow<IllegalArgumentException> {
            WorkExperience.create(
                WorkExperienceCreateRequest(
                    "레쥬미",
                    YearMonth.of(2025, 7),
                    YearMonth.of(2025, 6),
                    EmploymentType.FULL_TIME,
                    "백엔드 엔지니어",
                    "개발자",
                    "레쥬미 백엔드 개발함",
                ),
            )
        }
    }
})
