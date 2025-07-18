package app.resume.domain

class Resume private constructor(
    val title: String,
    val name: String,
    val email: Email,
    val callingCode: String,
    val nationalNumber: String,
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
            return Resume(title, name, Email(email), callingCode, nationalNumber,)
        }
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