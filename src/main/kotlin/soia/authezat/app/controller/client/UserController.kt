package soia.authezat.app.controller.client

import org.springframework.web.bind.annotation.*
import soia.authezat.app.controller.access.payloads.RolePayload
import soia.authezat.domain.service.account.UserService
import soia.authezat.domain.service.client.ClientService

@RestController("clientUserController")
@RequestMapping("/private/account/users")
class UserController(
    private val clientService: ClientService,
    private val userService: UserService,
) {

    @GetMapping("/{userSrl}/roles")
    fun getRolesBySrl(
        @PathVariable userSrl: Long,
        @RequestParam name: String,
        @RequestParam key: String,
    ): List<RolePayload> {
        require(clientService.existByNameAndKey(name = name, key = key))

        return userService.getRolesBySrl(userSrl).map { RolePayload(srl = userSrl, name = it.name) }
    }

}