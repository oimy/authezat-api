package soia.authezat.app.controller.server

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import soia.authezat.app.controller.access.payloads.RolePayload
import soia.authezat.app.controller.server.payloads.EndpointPayload
import soia.authezat.app.utils.asLocal
import soia.authezat.domain.service.server.EndpointService
import java.time.OffsetDateTime

@RestController
@RequestMapping("/server/endpoints")
class EndpointController(
    private val endpointService: EndpointService,
) {

    @GetMapping("")
    fun findAllByModifiedAtGreaterThenFetchRole(@RequestParam fromModifiedAt: OffsetDateTime): List<EndpointPayload> =
        endpointService.findAllByModifiedAtGreaterThenFetchRole(fromModifiedAt = fromModifiedAt.asLocal())
            .map { endpoint ->
                val roles: List<RolePayload> = endpoint.roles.map { RolePayload(name = it.name) }
                EndpointPayload(
                    serverSrl = endpoint.serverSrl,
                    method = endpoint.method,
                    path = endpoint.path,
                    roles = roles
                )
            }

}