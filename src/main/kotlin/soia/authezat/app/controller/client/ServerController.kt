package soia.authezat.app.controller.client

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import soia.authezat.app.controller.access.payloads.RolePayload
import soia.authezat.app.controller.server.payloads.EndpointPayload
import soia.authezat.app.controller.server.payloads.ServerPayload
import soia.authezat.app.utils.asLocal
import soia.authezat.domain.service.client.ClientService
import soia.authezat.domain.service.server.EndpointService
import soia.authezat.domain.service.server.ServerService
import java.time.OffsetDateTime

@RestController("clientServerController")
@RequestMapping("/private/server")
class ServerController(
    private val clientService: ClientService,
    private val serverService: ServerService,
    private val endpointService: EndpointService,
) {

    @GetMapping("/servers")
    fun getServers(
        @RequestParam name: String,
        @RequestParam key: String,
    ): List<ServerPayload> {
        require(clientService.existByNameAndKey(name = name, key = key))

        return serverService.findAll()
            .map { ServerPayload(srl = it.srl, name = it.name, url = it.url, version = it.version) }
    }

    @GetMapping("/endpoints")
    fun getEndpointsByModifiedAtGreaterThenFetchRole(
        @RequestParam afterModifiedAt: OffsetDateTime,
        @RequestParam name: String,
        @RequestParam key: String,
    ): List<EndpointPayload> {
        require(clientService.existByNameAndKey(name = name, key = key))

        return endpointService
            .findAllByModifiedAtGreaterThenFetchRole(afterModifiedAt = afterModifiedAt.asLocal())
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
    }
}