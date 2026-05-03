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

    @GetMapping("/{srl}")
    fun findBySrl(@PathVariable srl: Long): UserPayload =
        UserPayload(name = userService.findBySrl(srl).name)

    @GetMapping("/{srl}/roles")
    fun getRolesBySrl(@PathVariable srl: Long): List<RolePayload> =
        userService.getRolesBySrl(srl).map { RolePayload(name = it.name) }

}