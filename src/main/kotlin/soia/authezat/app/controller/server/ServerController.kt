package soia.authezat.app.controller.server

import org.springframework.web.bind.annotation.*
import soia.authezat.app.controller.server.payloads.EndpointPayload
import soia.authezat.app.controller.server.payloads.EndpointSavePayload
import soia.authezat.app.controller.server.payloads.ServerPayload
import soia.authezat.domain.service.server.EndpointService
import soia.authezat.domain.service.server.ServerService
import soia.authezat.infra.database.configuration.auditor.annotations.AuditAccessedBy
import soia.authezat.infra.database.configuration.auditor.annotations.AuditCreatedBy
import soia.authezat.infra.database.configuration.auditor.annotations.Audited

@RestController
@RequestMapping("/server/servers")
class ServerController(
    private val serverService: ServerService,
    private val endpointService: EndpointService,
) {

    @GetMapping
    @AuditAccessedBy
    fun findAll(@Audited accessedBy: String): List<ServerPayload> =
        serverService.findAll(accessedBy)
            .map { ServerPayload(srl = it.srl, name = it.name, url = it.url, version = it.version) }

    @PostMapping("/{serverSrl}/endpoints")
    @AuditCreatedBy
    fun saveEndpoints(
        @PathVariable serverSrl: Long,
        @RequestBody endpointSaves: List<EndpointSavePayload>,
        @Audited createdBy: String,
    ) =
        endpointService.saveAll(serverSrl = serverSrl, endpointSaves = endpointSaves, createdBy = createdBy)

    @GetMapping("/{serverSrl}/endpoints")
    @AuditAccessedBy
    fun findAllEndpointsByServerSrl(@PathVariable serverSrl: Long, @Audited accessedBy: String): List<EndpointPayload> =
        endpointService.findAllByServerSrl(serverSrl, accessedBy = accessedBy).map {
            EndpointPayload(
                srl = it.srl,
                serverSrl = it.serverSrl,
                method = it.method,
                path = it.path
            )
        }

}