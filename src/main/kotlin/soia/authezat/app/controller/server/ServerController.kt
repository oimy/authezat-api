package soia.authezat.app.controller.server

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import soia.authezat.app.controller.access.payloads.RolePayload
import soia.authezat.app.controller.server.payloads.EndpointDetailPayload
import soia.authezat.app.controller.server.payloads.EndpointPayloadFetchRoleAndDetail
import soia.authezat.app.controller.server.payloads.EndpointSavePayload
import soia.authezat.app.controller.server.payloads.ServerPayload
import soia.authezat.domain.service.server.EndpointService
import soia.authezat.domain.service.server.ServerService
import soia.authezat.infra.database.configuration.auditor.annotations.AuditAccessedBy
import soia.authezat.infra.database.configuration.auditor.annotations.AuditCreatedBy
import soia.authezat.infra.database.configuration.auditor.annotations.Audited
import java.util.*

@RestController
@RequestMapping("/server/servers")
class ServerController(
    private val serverService: ServerService,
    private val endpointService: EndpointService,
) {

    @GetMapping
    fun findAll(): List<ServerPayload> =
        serverService.findAll()
            .map { ServerPayload(srl = it.srl, name = it.name, url = it.url, version = it.version) }

    @GetMapping("/self")
    @AuditAccessedBy
    fun findAllBySelf(@Audited accessedBy: UUID): List<ServerPayload> =
        serverService.findAllByUserId(accessedBy)
            .map { ServerPayload(srl = it.srl, name = it.name, url = it.url, version = it.version) }

    @PostMapping("/{serverSrl}/endpoints/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    @AuditCreatedBy
    fun saveEndpoints(
        @PathVariable serverSrl: Long,
        @RequestBody endpointSaves: List<EndpointSavePayload>,
        @Audited createdBy: UUID,
    ) =
        endpointService.saveAll(serverSrl = serverSrl, endpointSaves = endpointSaves, createdBy = createdBy)

    @GetMapping("/{serverSrl}/endpoints")
    @AuditAccessedBy
    fun findAllEndpointsByServerSrl(@PathVariable serverSrl: Long, @Audited accessedBy: UUID): List<EndpointPayloadFetchRoleAndDetail> =
        endpointService.findAllByServerSrlAndUserIdFetchRolesAndDetail(serverSrl = serverSrl, userId = accessedBy).map {
            val roles: List<RolePayload> = it.roles.map { role -> RolePayload(srl = role.srl, name = role.name) }
            val detail = it.detail.let { detail ->
                EndpointDetailPayload(
                    operationId = detail.operationId,
                    summary = detail.summary,
                    description = detail.description,
                    tags = detail.tags,
                    variables = detail.variables,
                    parameters = detail.parameters,
                    requestBodies = detail.requestBodies,
                    responseBodies = detail.responseBodies,
                )
            }
            EndpointPayloadFetchRoleAndDetail(
                srl = it.srl,
                serverSrl = it.serverSrl,
                method = it.method,
                path = it.path,
                roles = roles,
                detail = detail,
            )
        }

}