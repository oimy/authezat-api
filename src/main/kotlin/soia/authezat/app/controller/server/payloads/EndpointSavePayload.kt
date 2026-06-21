package soia.authezat.app.controller.server.payloads

import soia.authezat.infra.database.dolphin.server.enums.EndpointMethod

data class EndpointSavePayload(
    val method: EndpointMethod,
    val path: String,
    val detail: EndpointDetailSavePayload
)