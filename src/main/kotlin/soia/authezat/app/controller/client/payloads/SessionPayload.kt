package soia.authezat.app.controller.client.payloads

import java.time.OffsetDateTime
import java.util.UUID

data class SessionPayload(
    val sessionKey: String,
    val expiredAt: OffsetDateTime,
    val userSrl: Long,
    val userId: UUID,
)