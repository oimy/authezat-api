package soia.authezat.app.controller.server

import org.springframework.web.bind.annotation.*
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
    fun findAllByModifiedAtGreaterThenFetchRole(@RequestParam afterModifiedAt: OffsetDateTime): List<EndpointPayload> =
        endpointService.findAllByModifiedAtGreaterThenFetchRole(afterModifiedAt = afterModifiedAt.asLocal())
            .map { endpoint ->
                val roles: List<RolePayload> = endpoint.roles.map { RolePayload(name = it.name) }
                EndpointPayload(
                    srl = endpoint.srl,
                    serverSrl = endpoint.serverSrl,
                    method = endpoint.method,
                    path = endpoint.path,
                    roles = roles
                )
            }

    @GetMapping("/{srl}/roles")
    fun getRolesBySrl(@PathVariable srl: Long): List<RolePayload> =
        endpointService.findBySrlFetchRole(srl).roles.map { RolePayload(name = it.name) }

}