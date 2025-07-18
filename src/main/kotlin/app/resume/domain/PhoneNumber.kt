package app.resume.domain

data class PhoneNumber(
    val callingCode: String,
    val nationalNumber: String,
) {
    init {
        require(CALLING_CODE_PATTERN.matches(callingCode)) {
            "Invalid calling code: $callingCode"
        }

        require(NATIONAL_NUMBER_PATTERN.matches(nationalNumber)) {
            "Invalid national number: $nationalNumber"
        }
    }

    companion object {
        private val CALLING_CODE_PATTERN = Regex("^\\d{1,4}$")  // 국제 전화 정규식 패턴(숫자 4자리)
        private val NATIONAL_NUMBER_PATTERN = Regex("^\\d{5,15}$") // 국내 번호 정규식 패턴(숫자 5-15자리)

    }
}