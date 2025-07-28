package app.resume.domain.resume

import app.resume.domain.resume.ResumeFixture.createResumeCreateRequest
import app.resume.domain.member.MemberFixture.createMember
import app.resume.domain.resume.ResumeFixture.createWorkExperienceRequest
import app.resume.domain.shared.Email
import app.resume.domain.shared.PhoneNumber
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.time.YearMonth

class ResumeTest : StringSpec({
    val writer = createMember()
    writer.activate()

    val actual = Resume.create(createResumeCreateRequest(writer))

    "이력서 생성" {
        actual.writer.isActive() shouldBe true
        actual.title shouldBe "title"
        actual.name shouldBe "문파관작"
        actual.email shouldBe Email("y2gcoder@gmail.com")
        actual.phoneNumber shouldBe PhoneNumber("82", "1012341234")
        actual.createdAt shouldNotBe null
    }

    "이력서 생성 실패 - 회원이 등록 완료 상태가 아님" {
        val pendingWriter = createMember()

        shouldThrow<IllegalArgumentException> { Resume.create(createResumeCreateRequest(pendingWriter)) }
    }

    "소제목 변경" {
        actual.subtitle shouldBe null

        actual.updateSubtitle("나는 멋진 백엔드 개발자!")

        actual.subtitle shouldBe "나는 멋진 백엔드 개발자!"

        actual.updateSubtitle(null)

        actual.subtitle shouldBe null
    }

    "프로필 이미지 URL 변경" {
        actual.profileImageUrl shouldBe null

        actual.updateProfileImageUrl("https://avatars.githubusercontent.com/y2gcoder.png")

        actual.profileImageUrl shouldBe "https://avatars.githubusercontent.com/y2gcoder.png"

        actual.updateProfileImageUrl(null)

        actual.profileImageUrl shouldBe null
    }

    "한줄 소개 변경" {
        actual.bio shouldBe null

        actual.updateBio("안녕하세요! 저는 백엔드 개발자 문파관작입니다 :) ")

        actual.bio shouldBe "안녕하세요! 저는 백엔드 개발자 문파관작입니다 :) "

        actual.updateBio(null)

        actual.bio shouldBe null
    }

    "제목 업데이트" {
        actual.title shouldBe "title"

        actual.updateTitle("new title")

        actual.title shouldBe "new title"

        shouldThrow<IllegalArgumentException> {
            actual.updateTitle(" ")
        }
    }

    "이름 업데이트" {
        actual.name shouldBe "문파관작"

        actual.updateName("우일신")

        actual.name shouldBe "우일신"

        shouldThrow<IllegalArgumentException> {
            actual.updateName(" ")
        }
    }

    "이메일 업데이트" {
        actual.email shouldBe Email("y2gcoder@gmail.com")

        val newEmail = Email("y3gcoder@gmail.com")

        actual.updateEmail(newEmail)

        actual.email shouldBe newEmail
    }

    "휴대폰 번호 업데이트" {
        actual.phoneNumber shouldBe PhoneNumber("82", "1012341234")

        actual.updatePhoneNumber(PhoneNumber("1", "1078907890"))

        actual.phoneNumber shouldBe PhoneNumber("1", "1078907890")
    }

    "경력 추가" {
        val createWorkExperienceRequest = createWorkExperienceRequest()

        actual.addWorkExperience(createWorkExperienceRequest)

        actual.workExperiences shouldHaveSize 1
        actual.workExperiences[0].companyName shouldBe "레쥬미"
        actual.workExperiences[0].workPeriod.startedAt shouldBe YearMonth.of(2025, 7)
        actual.workExperiences[0].workPeriod.endedAt shouldBe null
        actual.workExperiences[0].employmentType shouldBe EmploymentType.FULL_TIME
        actual.workExperiences[0].role shouldBe "백엔드 엔지니어"
        actual.workExperiences[0].position shouldBe "개발자"
        actual.workExperiences[0].achievement shouldBe "레쥬미 백엔드 개발함"

        actual.workExperiences[0].isCurrentJob() shouldBe true
    }

    "경력 추가 실패 - 재직기간 시작일시가 종료일시보다 미래임" {
        val createWorkExperienceRequest = createWorkExperienceRequest(
            startedAt = YearMonth.of(2025, 7),
            endedAt = YearMonth.of(2025, 6),
        )

        shouldThrow<IllegalArgumentException> {
            actual.addWorkExperience(createWorkExperienceRequest)
        }
    }
})
