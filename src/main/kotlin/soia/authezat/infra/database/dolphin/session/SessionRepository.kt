package soia.authezat.infra.database.dolphin.session

import org.springframework.data.jpa.repository.JpaRepository

interface SessionRepository : JpaRepository<SessionEntity, Int> {

    fun findBySessionKey(key: String): SessionEntity?

}