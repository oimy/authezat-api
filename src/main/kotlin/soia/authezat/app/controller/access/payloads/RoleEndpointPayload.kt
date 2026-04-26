package soia.authezat.app.controller.access.payloads

import soia.authezat.app.controller.server.payloads.EndpointPayload

data class RoleEndpointPayload(
    val role: RolePayload,
    val endpoint: EndpointPayload,
)