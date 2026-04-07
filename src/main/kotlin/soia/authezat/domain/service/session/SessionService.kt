package soia.authezat.domain.service.session

import soia.authezat.domain.service.session.values.Session
import java.time.LocalDateTime

interface SessionService {

    fun clearAndSave(userSrl: Long, sessionKey: String, expiredAt: LocalDateTime): Session

    fun findBySessionKey(key: String): Session

}