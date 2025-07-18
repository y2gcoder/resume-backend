package app.resume.domain

class Resume private constructor(
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
            title: String,
            name: String,
            email: String,
            callingCode: String,
            nationalNumber: String,
        ): Resume {
            require(!title.isBlank()) { "title can't be blank" }
            require(!name.isBlank()) { "name can't be blank" }
            return Resume(title, name, Email(email), PhoneNumber(callingCode, nationalNumber))
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