package soia.authezat.app.controller.server

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import soia.authezat.app.controller.server.payloads.ServerPayload
import soia.authezat.domain.service.server.ServerService

@RestController
@RequestMapping("/server/servers")
class ServerController(
    private val serverService: ServerService
) {

    @GetMapping
    fun findAll(): List<ServerPayload> =
        serverService.findAll().map {
            ServerPayload(name = it.name, url = it.url, version = it.version)
        }

}