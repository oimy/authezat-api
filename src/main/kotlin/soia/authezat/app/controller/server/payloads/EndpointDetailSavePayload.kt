package soia.authezat.app.controller.server.payloads

data class EndpointDetailSavePayload(
    val tags: List<String>,
    val operationId: String,
    val summary: String?,
    val description: String?,
    val variables: List<Map<String, Any>>,
    val parameters: List<Map<String, Any>>,
    val requestBodies: List<Map<String, Any>>,
    val responseBodies: List<Map<String, Any>>,
)