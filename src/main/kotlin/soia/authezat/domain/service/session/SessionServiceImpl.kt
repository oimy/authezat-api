package soia.authezat.domain.service.session

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import soia.authezat.domain.service.session.values.Session
import soia.authezat.infra.database.dolphin.account.UserEntity
import soia.authezat.infra.database.dolphin.account.UserRepository
import soia.authezat.infra.database.dolphin.session.SessionEntity
import soia.authezat.infra.database.dolphin.session.SessionRepository
import java.time.LocalDateTime

@Service(value = "sessionService")
class SessionServiceImpl(
    private val sessionRepository: SessionRepository,
    private val userRepository: UserRepository,
) :
    SessionService {

    @Transactional
    override fun clearAndSave(userSrl: Long, sessionKey: String, expiredAt: LocalDateTime): Session {
        val userEntity: UserEntity = userRepository.findByIdOrNullFetchSession(userSrl)
            ?: throw EntityNotFoundException()

        userEntity.sessions.clear()
        userRepository.save(userEntity)
        userRepository.flush()

        val sessionEntity = SessionEntity(user = userEntity, sessionKey = sessionKey, expiredAt = expiredAt)
        val savedSessionEntity = sessionRepository.save(sessionEntity)
        userEntity.sessions.add(savedSessionEntity)
        return Session(savedSessionEntity)
    }

    @Transactional(readOnly = true)
    override fun findBySessionKey(key: String): Session =
        sessionRepository.findBySessionKey(key)?.let { Session(it) }
            ?: throw EntityNotFoundException()

}