package soia.authezat.app.controller.session.payloads

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class SessionSavePayload(
    @field:NotBlank
    val username: String,
    @field:NotBlank
    @Size(min = 8, max = 64)
    val password: String,
)