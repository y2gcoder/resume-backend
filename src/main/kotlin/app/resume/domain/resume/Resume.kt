package app.resume.domain.resume

import app.resume.domain.AbstractEntity
import app.resume.domain.member.Member
import app.resume.domain.shared.Email
import app.resume.domain.shared.PhoneNumber
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.Instant

@Entity
class Resume private constructor(

    /** 작성자 **/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    val writer: Member,

    /** 이력서 제목 **/
    var title: String,

    /** 지원자 이름 **/
    var name: String,

    /** 지원자 이메일 **/
    @Embedded
    var email: Email,

    /** 지원자 휴대폰 연락처 **/
    @Embedded
    var phoneNumber: PhoneNumber,

    /** 이력서 부제 **/
    var subtitle: String? = null,

    /** 프로필 이미지 URL **/
    var profileImageUrl: String? = null,

    /** 한줄 소개 **/
    var bio: String? = null,

    /** 작성일시 **/
    val createdAt: Instant = Instant.now(),
) : AbstractEntity() {
    companion object {
        fun create(
            createRequest: ResumeCreateRequest
        ): Resume {
            require(!createRequest.title.isBlank()) { "title can't be blank" }
            require(!createRequest.name.isBlank()) { "name can't be blank" }
            require(createRequest.writer.isActive()) { "이력서는 활성화된 회원만 작성할 수 있습니다" }

            return Resume(
                createRequest.writer,
                createRequest.title,
                createRequest.name,
                Email(createRequest.email),
                PhoneNumber(createRequest.callingCode, createRequest.nationalNumber),
            )
        }
    }

    fun updateTitle(title: String) {
        require(!title.isBlank()) { "title can't be blank" }
        this.title = title
    }

    fun updateName(name: String) {
        require(!name.isBlank()) { "name can't be blank" }
        this.name = name
    }

    fun updateEmail(email: Email) {
        this.email = email
    }


    fun updatePhoneNumber(phoneNumber: PhoneNumber) {
        this.phoneNumber = phoneNumber
    }

    fun updateSubtitle(subtitle: String?) {
        this.subtitle = subtitle
    }

    fun updateProfileImageUrl(profileImageUrl: String?) {
        this.profileImageUrl = profileImageUrl
    }

    fun updateBio(bio: String?) {
        this.bio = bio
    }
}