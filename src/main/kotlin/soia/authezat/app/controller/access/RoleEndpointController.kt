package soia.authezat.app.controller.access

import org.springframework.web.bind.annotation.*
import soia.authezat.app.controller.access.payloads.RoleEndpointPayload
import soia.authezat.app.controller.access.payloads.RoleEndpointPayloadSave
import soia.authezat.app.controller.access.payloads.RolePayload
import soia.authezat.app.controller.server.payloads.EndpointPayload
import soia.authezat.domain.service.access.RoleEndpointService
import soia.authezat.domain.service.access.values.RoleEndpoint
import soia.authezat.infra.database.configuration.auditor.annotations.AuditCreatedBy
import java.time.OffsetDateTime

@RestController
@RequestMapping("/access/roles-endpoints")
class RoleEndpointController(
    private val roleEndpointService: RoleEndpointService
) {

    @PostMapping
    @AuditCreatedBy
    fun save(@RequestBody roleEndpointSave: RoleEndpointPayloadSave) =
        roleEndpointService.save(roleSrl = roleEndpointSave.roleSrl, endpointSrl = roleEndpointSave.endpointSrl)

    @GetMapping
    fun getEndpoints(@RequestParam(required = false) afterModifiedAt: OffsetDateTime? = null): List<RoleEndpointPayload> {
        val roleEndpoints: List<RoleEndpoint> = if (afterModifiedAt == null) {
            roleEndpointService.findAll()
        } else {
            roleEndpointService.findByModifiedAtGreaterThan(modifiedAt = afterModifiedAt.toLocalDateTime())
        }

        return roleEndpoints.map {
            RoleEndpointPayload(
                role = RolePayload(name = it.role.name),
                endpoint = EndpointPayload(
                    serverSrl = it.endpoint.serverSrl,
                    path = it.endpoint.path,
                    method = it.endpoint.method
                )
            )
        }
    }

}