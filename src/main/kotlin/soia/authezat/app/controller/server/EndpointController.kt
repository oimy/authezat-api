package soia.authezat.app.controller.server

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import soia.authezat.app.controller.server.payloads.EndpointPayload
import soia.authezat.domain.service.server.EndpointService

@RestController
@RequestMapping("/server/servers")
class EndpointController(
    private val endpointService: EndpointService,
) {

    @RequestMapping("/{serverSrl}/endpoints")
    fun findAllByServerSrl(@PathVariable serverSrl: Long): List<EndpointPayload> =
        endpointService.findAllByServerSrl(serverSrl).map {
            EndpointPayload(serverSrl = it.serverSrl, method = it.method, path = it.path)
        }

}