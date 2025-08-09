package app.resume.domain.resume

import app.resume.domain.member.Member
import app.resume.domain.member.MemberFixture.createMember
import app.resume.domain.resume.ResumeFixture.createPortfolioCreateRequest
import app.resume.domain.resume.ResumeFixture.createResumeCreateRequest
import app.resume.domain.resume.ResumeFixture.createWorkExperienceRequest
import app.resume.domain.shared.Email
import app.resume.domain.shared.PhoneNumber
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.time.Instant
import java.time.YearMonth

class ResumeTest : StringSpec({
    lateinit var writer: Member
    lateinit var resume: Resume

    beforeEach {
        writer = createMember()
        writer.activate()
        resume = Resume.create(createResumeCreateRequest(writer))
    }

    "이력서 생성" {
        resume.writer.isActive() shouldBe true
        resume.title shouldBe "title"
        resume.name shouldBe "문파관작"
        resume.email shouldBe Email("y2gcoder@gmail.com")
        resume.phoneNumber shouldBe PhoneNumber("82", "1012341234")
        resume.createdAt shouldNotBe null
    }

    "이력서 생성 실패 - 회원이 등록 완료 상태가 아님" {
        val pendingWriter = createMember()

        shouldThrow<IllegalArgumentException> { Resume.create(createResumeCreateRequest(pendingWriter)) }
    }

    "소제목 변경" {
        resume.subtitle shouldBe null

        resume.updateSubtitle("나는 멋진 백엔드 개발자!")

        resume.subtitle shouldBe "나는 멋진 백엔드 개발자!"

        resume.updateSubtitle(null)

        resume.subtitle shouldBe null
    }

    "프로필 이미지 URL 변경" {
        resume.profileImageUrl shouldBe null

        resume.updateProfileImageUrl("https://avatars.githubusercontent.com/y2gcoder.png")

        resume.profileImageUrl shouldBe "https://avatars.githubusercontent.com/y2gcoder.png"

        resume.updateProfileImageUrl(null)

        resume.profileImageUrl shouldBe null
    }

    "한줄 소개 변경" {
        resume.bio shouldBe null

        resume.updateBio("안녕하세요! 저는 백엔드 개발자 문파관작입니다 :) ")

        resume.bio shouldBe "안녕하세요! 저는 백엔드 개발자 문파관작입니다 :) "

        resume.updateBio(null)

        resume.bio shouldBe null
    }

    "제목 업데이트" {
        resume.title shouldBe "title"

        resume.updateTitle("new title")

        resume.title shouldBe "new title"

        shouldThrow<IllegalArgumentException> {
            resume.updateTitle(" ")
        }
    }

    "이름 업데이트" {
        resume.name shouldBe "문파관작"

        resume.updateName("우일신")

        resume.name shouldBe "우일신"

        shouldThrow<IllegalArgumentException> {
            resume.updateName(" ")
        }
    }

    "이메일 업데이트" {
        resume.email shouldBe Email("y2gcoder@gmail.com")

        val newEmail = Email("y3gcoder@gmail.com")

        resume.updateEmail(newEmail)

        resume.email shouldBe newEmail
    }

    "휴대폰 번호 업데이트" {
        resume.phoneNumber shouldBe PhoneNumber("82", "1012341234")

        resume.updatePhoneNumber(PhoneNumber("1", "1078907890"))

        resume.phoneNumber shouldBe PhoneNumber("1", "1078907890")
    }

    "경력 추가" {
        val createWorkExperienceRequest = createWorkExperienceRequest()

        resume.addWorkExperience(createWorkExperienceRequest)

        resume.workExperiences shouldHaveSize 1
        resume.workExperiences[0].companyName shouldBe "레쥬미"
        resume.workExperiences[0].workPeriod.startedAt shouldBe YearMonth.of(2025, 7)
        resume.workExperiences[0].workPeriod.endedAt shouldBe null
        resume.workExperiences[0].employmentType shouldBe EmploymentType.FULL_TIME
        resume.workExperiences[0].role shouldBe "백엔드 엔지니어"
        resume.workExperiences[0].position shouldBe "개발자"
        resume.workExperiences[0].achievement shouldBe "레쥬미 백엔드 개발함"

        resume.workExperiences[0].isCurrentJob() shouldBe true
    }

    "경력 추가 실패 - 재직기간 시작일시가 종료일시보다 미래임" {
        val createWorkExperienceRequest = createWorkExperienceRequest(
            startedAt = YearMonth.of(2025, 7),
            endedAt = YearMonth.of(2025, 6),
        )

        shouldThrow<IllegalArgumentException> {
            resume.addWorkExperience(createWorkExperienceRequest)
        }
    }

    "포트폴리오 추가" {
        resume.portfolio shouldBe null

        val portfolioCreateRequest = createPortfolioCreateRequest()

        resume.createPortfolio(portfolioCreateRequest)

        resume.portfolio.shouldNotBeNull()
        resume.portfolio!!.links shouldHaveSize 2
        resume.portfolio!!.attachments shouldHaveSize 1
    }

    "포트폴리오 링크 업데이트" {
        val portfolioCreateRequest = createPortfolioCreateRequest()
        
        resume.createPortfolio(portfolioCreateRequest)
        resume.portfolio!!.links shouldHaveSize 2

        val newLinks = listOf(
            LinkItemCreateRequest("https://linkedin.com/in/y2gcoder", "링크드인"),
            LinkItemCreateRequest("https://blog.y2gcoder.com", "블로그")
        )

        resume.updatePortfolioLinks(newLinks)

        resume.portfolio!!.links shouldHaveSize 2
        resume.portfolio!!.links[0].url shouldBe "https://linkedin.com/in/y2gcoder"
        resume.portfolio!!.links[1].url shouldBe "https://blog.y2gcoder.com"
    }

    "포트폴리오 첨부파일 업데이트" {
        val portfolioCreateRequest = createPortfolioCreateRequest()
        
        resume.createPortfolio(portfolioCreateRequest)
        resume.portfolio!!.attachments shouldHaveSize 1

        val newAttachments = listOf(
            AttachmentItemCreateRequest("fileKey2", "document2", "application/pdf", 2048, Instant.now()),
            AttachmentItemCreateRequest("fileKey3", "image", "image/png", 512, Instant.now())
        )

        resume.updatePortfolioAttachments(newAttachments)

        resume.portfolio!!.attachments shouldHaveSize 2
        resume.portfolio!!.attachments[0].filename shouldBe "document2"
        resume.portfolio!!.attachments[1].filename shouldBe "image"
    }

    "포트폴리오 없을 때 업데이트 실패" {
        shouldThrow<IllegalStateException> {
            resume.updatePortfolioLinks(emptyList())
        }

        shouldThrow<IllegalStateException> {
            resume.updatePortfolioAttachments(emptyList())
        }
    }
})

