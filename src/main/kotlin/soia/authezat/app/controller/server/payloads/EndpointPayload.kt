package soia.authezat.app.controller.server.payloads

import soia.authezat.app.controller.access.payloads.RolePayload
import soia.authezat.infra.database.dolphin.server.enums.EndpointMethod

data class EndpointPayload(
    val serverSrl: Long,
    val method: EndpointMethod,
    val path: String,
    val roles: List<RolePayload> = emptyList(),
)