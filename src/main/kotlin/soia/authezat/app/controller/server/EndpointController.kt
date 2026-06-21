package soia.authezat.app.controller.server

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import soia.authezat.app.controller.access.payloads.RolePayload
import soia.authezat.domain.service.server.EndpointService
import soia.authezat.infra.database.configuration.auditor.annotations.AuditCreatedBy
import soia.authezat.infra.database.configuration.auditor.annotations.AuditDeletedBy
import soia.authezat.infra.database.configuration.auditor.annotations.Audited
import java.util.*

@RestController
@RequestMapping("/server/endpoints")
class EndpointController(
    private val endpointService: EndpointService,
) {

    @GetMapping("/{endpointSrl}/roles")
    fun getRolesBySrl(@PathVariable endpointSrl: Long): List<RolePayload> =
        endpointService.findBySrlFetchRole(endpointSrl).roles.map { RolePayload(srl = it.srl, name = it.name) }

    @PostMapping("/{endpointSrl}/roles/{roleSrl}")
    @ResponseStatus(HttpStatus.CREATED)
    @AuditCreatedBy
    fun addRole(@PathVariable endpointSrl: Long, @PathVariable roleSrl: Long, @Audited addedBy: UUID) =
        endpointService.addRole(srl = endpointSrl, roleSrl = roleSrl, addedBy = addedBy)

    @DeleteMapping("/{endpointSrl}/roles/{roleSrl}")
    @AuditDeletedBy
    fun removeRole(@PathVariable endpointSrl: Long, @PathVariable roleSrl: Long, @Audited removedBy: UUID) =
        endpointService.removeRole(srl = endpointSrl, roleSrl = roleSrl, removedBy = removedBy)

}