package app.resume.domain.resume

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class ResumeCreateRequest(
    @field:NotBlank val title: String,
    @field:NotBlank val name: String,
    @field:NotBlank val email: String,
    @field:Size(min = 1, max = 4) val callingCode: String,
    @field:Size(min = 5, max = 15) val nationalNumber: String,
)
