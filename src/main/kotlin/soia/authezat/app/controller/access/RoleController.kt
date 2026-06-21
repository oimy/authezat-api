package soia.authezat.app.controller.access

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import soia.authezat.app.controller.access.payloads.RolePayload
import soia.authezat.app.controller.access.payloads.RoleSavePayload
import soia.authezat.domain.service.access.RoleService
import soia.authezat.infra.database.configuration.auditor.annotations.AuditAccessedBy
import soia.authezat.infra.database.configuration.auditor.annotations.AuditCreatedBy
import soia.authezat.infra.database.configuration.auditor.annotations.Audited
import java.util.*

@RestController
@RequestMapping("/access/roles")
class RoleController(
    private val roleService: RoleService,
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @AuditCreatedBy
    fun save(@RequestBody roleSave: RoleSavePayload, @Audited createdBy: UUID) =
        roleService.save(name = roleSave.name, createdBy = createdBy)

    @GetMapping
    fun findAll(): List<RolePayload> =
        roleService.findAll().map { RolePayload(srl = it.srl, name = it.name) }

    @GetMapping("/self")
    @AuditAccessedBy
    fun findAllByAccessedBy(@Audited accessedBy: UUID) =
        roleService.findAllByUserId(userId = accessedBy).map { RolePayload(srl = it.srl, name = it.name) }

}