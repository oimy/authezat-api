package soia.authezat.app.controller.server

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import soia.authezat.app.controller.access.payloads.RolePayload
import soia.authezat.app.controller.server.payloads.EndpointPayload
import soia.authezat.app.utils.asLocal
import soia.authezat.domain.service.server.EndpointService
import soia.authezat.infra.database.configuration.auditor.annotations.AuditCreatedBy
import soia.authezat.infra.database.configuration.auditor.annotations.AuditDeletedBy
import soia.authezat.infra.database.configuration.auditor.annotations.Audited
import java.time.OffsetDateTime

@RestController
@RequestMapping("/server/endpoints")
class EndpointController(
    private val endpointService: EndpointService,
) {

    @GetMapping
    fun findAllByModifiedAtGreaterThenFetchRole(@RequestParam afterModifiedAt: OffsetDateTime): List<EndpointPayload> =
        endpointService.findAllByModifiedAtGreaterThenFetchRole(afterModifiedAt = afterModifiedAt.asLocal())
            .map { endpoint ->
                val roles: List<RolePayload> = endpoint.roles.map { RolePayload(srl = it.srl, name = it.name) }
                EndpointPayload(
                    srl = endpoint.srl,
                    serverSrl = endpoint.serverSrl,
                    method = endpoint.method,
                    path = endpoint.path,
                    roles = roles
                )
            }

    @GetMapping("/{endpointSrl}/roles")
    fun getRolesBySrl(@PathVariable endpointSrl: Long): List<RolePayload> =
        endpointService.findBySrlFetchRole(endpointSrl).roles.map { RolePayload(srl = it.srl, name = it.name) }

    @PostMapping("/{endpointSrl}/roles/{roleSrl}")
    @ResponseStatus(HttpStatus.CREATED)
    @AuditCreatedBy
    fun addRole(@PathVariable endpointSrl: Long, @PathVariable roleSrl: Long, @Audited addedBy: String) =
        endpointService.addRole(srl = endpointSrl, roleSrl = roleSrl, addedBy = addedBy)

    @DeleteMapping("/{endpointSrl}/roles/{roleSrl}")
    @ResponseStatus(HttpStatus.CREATED)
    @AuditDeletedBy
    fun removeRole(@PathVariable endpointSrl: Long, @PathVariable roleSrl: Long, @Audited removedBy: String) =
        endpointService.removeRole(srl = endpointSrl, roleSrl = roleSrl, removedBy = removedBy)

}