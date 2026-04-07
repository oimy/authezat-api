package soia.authezat.app.controller.account.payloads

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class AccountSavePayload(
    @field:NotBlank
    val username: String,
    @field:NotBlank
    @Size(min = 8, max = 64)
    val password: String,
    @field:NotBlank
    val name: String,
    @field:NotBlank
    val email: String,
)