package soia.authezat.app.controller.server

import org.springframework.web.bind.annotation.*
import soia.authezat.app.controller.server.payloads.EndpointPayload
import soia.authezat.app.controller.server.payloads.EndpointSavePayload
import soia.authezat.app.controller.server.payloads.ServerPayload
import soia.authezat.domain.service.server.EndpointService
import soia.authezat.domain.service.server.ServerService

@RestController
@RequestMapping("/server/servers")
class ServerController(
    private val serverService: ServerService,
    private val endpointService: EndpointService,
) {

    @GetMapping
    fun findAll(): List<ServerPayload> =
        serverService.findAll().map {
            ServerPayload(srl = it.srl, name = it.name, url = it.url, version = it.version)
        }

    @PostMapping("/{srl}/endpoints")
    fun saveEndpoint(@PathVariable srl: Long, endpointSave: EndpointSavePayload) =
        endpointService.save(serverSrl = srl, method = endpointSave.method, path = endpointSave.path)

    @GetMapping("/{srl}/endpoints")
    fun findAllEndpointsByServerSrl(@PathVariable srl: Long): List<EndpointPayload> =
        endpointService.findAllByServerSrl(srl).map {
            EndpointPayload(serverSrl = it.serverSrl, method = it.method, path = it.path)
        }

}