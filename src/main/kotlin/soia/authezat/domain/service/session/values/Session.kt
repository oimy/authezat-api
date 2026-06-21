package soia.authezat.domain.service.session.values

import soia.authezat.domain.service.base.BaseValue
import soia.authezat.infra.database.dolphin.session.SessionEntity
import java.time.LocalDateTime
import java.util.*

data class Session(
    val userSrl: Long,
    val sessionKey: String,
    val expiredAt: LocalDateTime,

    override val srl: Long,
    override val createdBy: UUID,
    override val createdAt: LocalDateTime,
    override val modifiedBy: UUID,
    override val modifiedAt: LocalDateTime,
) :
    BaseValue {

    constructor(session: SessionEntity) : this(
        userSrl = session.user.srl,
        sessionKey = session.sessionKey,
        expiredAt = session.expiredAt,
        srl = session.srl,
        createdBy = session.createdBy,
        createdAt = session.createdAt,
        modifiedBy = session.modifiedBy,
        modifiedAt = session.modifiedAt,
    )

}
