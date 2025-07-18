package app.resume.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ResumeTest : StringSpec({
    val actual = Resume.create(
        "title",
        "문파관작",
        "y2gcoder@gmail.com",
        "82",
        "1012341234",
    )

    "이력서 생성" {
        actual.title shouldBe "title"
        actual.name shouldBe "문파관작"
        actual.email shouldBe Email("y2gcoder@gmail.com")
        actual.callingCode shouldBe "82"
        actual.nationalNumber shouldBe "1012341234"
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
})
