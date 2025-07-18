package app.resume.domain

data class Email(val address: String) {

    init {
        require(isValid(address)) { "Invalid email address: $address" }
    }

    companion object {
        private val EMAIL_PATTERN =
            Regex("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")

        fun isValid(email: String): Boolean {
            return EMAIL_PATTERN.matches(email)
        }
    }
}