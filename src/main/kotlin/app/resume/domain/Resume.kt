package app.resume.domain

import app.resume.domain.member.Member
import app.resume.domain.shared.Email
import app.resume.domain.shared.PhoneNumber

class Resume private constructor(
    val writer: Member,
    var title: String,
    var name: String,
    var email: Email,
    var phoneNumber: PhoneNumber,
    var subtitle: String? = null,
    var profileImageUrl: String? = null,
    var bio: String? = null,
) {
    companion object {
        fun create(
            createRequest: ResumeCreateRequest
        ): Resume {
            require(!createRequest.title.isBlank()) { "title can't be blank" }
            require(!createRequest.name.isBlank()) { "name can't be blank" }
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