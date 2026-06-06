package soia.authezat.app.controller.account

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import soia.authezat.app.controller.access.payloads.RolePayload
import soia.authezat.app.controller.account.payloads.AccountSavePayload
import soia.authezat.app.controller.account.payloads.UserPayload
import soia.authezat.domain.service.account.UserService
import soia.authezat.infra.database.configuration.auditor.annotations.AuditCreatedBy

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

    @GetMapping("/{userSrl}")
    fun findBySrl(@PathVariable userSrl: Long): UserPayload =
        UserPayload(name = userService.findBySrl(userSrl).name)

    @GetMapping("/{userSrl}/roles")
    fun getRolesBySrl(@PathVariable userSrl: Long): List<RolePayload> =
        userService.getRolesBySrl(userSrl).map { RolePayload(srl = userSrl, name = it.name) }

}