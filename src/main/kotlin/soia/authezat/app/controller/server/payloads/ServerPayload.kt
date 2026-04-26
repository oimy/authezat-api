package soia.authezat.app.controller.server.payloads

data class ServerPayload(
    val name: String,
    val url: String,
    val version: Short
)