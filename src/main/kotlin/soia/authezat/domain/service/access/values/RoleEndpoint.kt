package soia.authezat.domain.service.access.values

import soia.authezat.domain.service.server.values.Endpoint

data class RoleEndpoint(
    val role: Role,
    val endpoint: Endpoint,
)