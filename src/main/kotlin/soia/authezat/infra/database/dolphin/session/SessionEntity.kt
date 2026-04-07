package soia.authezat.infra.database.dolphin.session

import jakarta.persistence.*
import soia.authezat.infra.database.dolphin.account.UserEntity
import soia.authezat.infra.database.dolphin.base.BaseEntity
import java.time.LocalDateTime

@Entity
@Table(name = "sessions")
class SessionEntity(
    @JoinColumn(updatable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    var user: UserEntity,

    var sessionKey: String,

    var expiredAt: LocalDateTime,
) :
    BaseEntity()