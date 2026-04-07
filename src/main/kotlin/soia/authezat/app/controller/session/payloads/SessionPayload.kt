package soia.authezat.app.controller.session.payloads

import java.time.OffsetDateTime

data class SessionPayload(
    val sessionKey: String,
    val expiredAt: OffsetDateTime,
)