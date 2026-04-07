package soia.authezat.app.controller.account

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import soia.authezat.app.controller.account.payloads.AccountSavePayload
import soia.authezat.domain.service.account.UserService
import soia.authezat.infra.configuration.auditor.annotations.AuditCreatedBy

@RestController
@RequestMapping("/account/users")
class UserController(
    private val userService: UserService,
) {

    @PostMapping("")
    @AuditCreatedBy
    fun save(@RequestBody @Valid accountSave: AccountSavePayload) =
        userService.save(
            name = accountSave.name,
            email = accountSave.email,
            username = accountSave.username,
            password = accountSave.password
        )

}