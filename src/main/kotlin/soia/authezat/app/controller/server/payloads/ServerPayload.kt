package soia.authezat.app.controller.server.payloads

data class ServerPayload(
    val srl: Long,
    val name: String,
    val url: String,
    val version: Short
)