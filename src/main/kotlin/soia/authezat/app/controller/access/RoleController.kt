package soia.authezat.app.controller.access

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import soia.authezat.app.controller.access.payloads.RolePayload
import soia.authezat.app.controller.access.payloads.RoleSavePayload
import soia.authezat.domain.service.access.RoleService
import soia.authezat.infra.database.configuration.auditor.annotations.AuditCreatedBy

@RestController
@RequestMapping("/access/roles")
class RoleController(
    private val roleService: RoleService,
) {

    @PostMapping
    @AuditCreatedBy
    fun save(@RequestBody roleSave: RoleSavePayload) =
        roleService.save(name = roleSave.name)

    @GetMapping
    fun findAll(@RequestParam pageable: Pageable): Page<RolePayload> =
        roleService.findAll(pageable).map { RolePayload(name = it.name) }

}