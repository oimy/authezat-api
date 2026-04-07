package soia.authezat.domain.service.session.values

import soia.authezat.domain.service.base.BaseValue
import soia.authezat.infra.database.dolphin.session.SessionEntity
import java.time.LocalDateTime

data class Session(
    val userSrl: Long,
    val sessionKey: String,
    val expiredAt: LocalDateTime,

    override val srl: Long,
    override val createdBy: String,
    override val createdAt: LocalDateTime,
    override val modifiedBy: String,
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
