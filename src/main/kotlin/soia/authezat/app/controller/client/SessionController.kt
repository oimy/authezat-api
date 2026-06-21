package soia.authezat.app.controller.client

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import soia.authezat.app.controller.client.payloads.SessionPayload
import soia.authezat.app.controller.client.payloads.SessionSavePayload
import soia.authezat.app.utils.asOffset
import soia.authezat.domain.service.account.UserService
import soia.authezat.domain.service.account.values.User
import soia.authezat.domain.service.client.ClientService
import soia.authezat.domain.service.session.SessionKeyService
import soia.authezat.domain.service.session.SessionService
import soia.authezat.domain.service.session.values.Session
import soia.authezat.infra.database.configuration.auditor.annotations.AuditCreatedBy
import java.time.LocalDateTime

@RestController("clientSessionController")
@RequestMapping("/private/session/sessions")
class SessionController(
    private val clientService: ClientService,
    private val sessionService: SessionService,
    private val sessionKeyService: SessionKeyService,
    private val userService: UserService,
) {

    companion object {
        private const val DEFAULT_SESSION_EXPIRED_MINUTE = 60
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @AuditCreatedBy
    fun clearAndSave(
        @RequestParam name: String,
        @RequestParam key: String,
        @RequestBody sessionSave: SessionSavePayload,
    ): SessionPayload {
        require(clientService.existByNameAndKey(name = name, key = key))

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
            userSrl = session.userSrl,
            userId = user.id
        )
    }

}