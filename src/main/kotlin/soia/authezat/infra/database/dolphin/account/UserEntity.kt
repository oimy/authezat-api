package soia.authezat.infra.database.dolphin.account

import jakarta.persistence.*
import soia.authezat.infra.database.dolphin.base.BaseEntity
import soia.authezat.infra.database.dolphin.session.SessionEntity

@Entity
@Table(name = "users")
class UserEntity(
    @JoinColumn(nullable = false, updatable = false)
    @OneToOne(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY)
    var sign: SignEntity,

    var name: String,

    var email: String,

    @OneToMany(mappedBy = "user", cascade = [(CascadeType.ALL)], orphanRemoval = true)
    var sessions: MutableList<SessionEntity> = mutableListOf(),
) :
    BaseEntity()