package soia.authezat.app.controller.session

import org.springframework.web.bind.annotation.*
import soia.authezat.app.controller.session.payloads.SessionPayload
import soia.authezat.app.controller.session.payloads.SessionSavePayload
import soia.authezat.app.utils.asOffset
import soia.authezat.domain.service.account.UserService
import soia.authezat.domain.service.account.values.User
import soia.authezat.domain.service.session.SessionKeyService
import soia.authezat.domain.service.session.SessionService
import soia.authezat.domain.service.session.values.Session
import soia.authezat.infra.configuration.auditor.annotations.AuditCreatedBy
import java.time.LocalDateTime

@RestController
@RequestMapping("/session/sessions")
class SessionController(
    private val sessionService: SessionService,
    private val sessionKeyService: SessionKeyService,
    private val userService: UserService,
) {

    companion object {
        private const val DEFAULT_SESSION_EXPIRED_MINUTE = 60
    }

    @PostMapping("")
    @AuditCreatedBy
    fun clearAndSave(@RequestBody sessionSave: SessionSavePayload): SessionPayload {
        val user: User = userService.getByUsernameAndPassword(
            username = sessionSave.username,
            password = sessionSave.password
        )
        val sessionKey: String = sessionKeyService.generate()
        val expiredAt: LocalDateTime = LocalDateTime.now().plusMinutes(DEFAULT_SESSION_EXPIRED_MINUTE.toLong())
        val session: Session = sessionService.clearAndSave(
            userSrl = user.srl,
            sessionKey = sessionKey,
            expiredAt = expiredAt
        )

        return SessionPayload(
            sessionKey = session.sessionKey,
            expiredAt = session.expiredAt.asOffset(),
            userSrl = session.userSrl
        )
    }

    @GetMapping("")
    fun findBySessionKey(@RequestParam sessionKey: String): SessionPayload =
        sessionService.findBySessionKey(key = sessionKey).let {
            SessionPayload(sessionKey = it.sessionKey, expiredAt = it.expiredAt.asOffset(), userSrl = it.userSrl)
        }

}