package soia.authezat.infra.database.dolphin.account

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import soia.authezat.infra.database.dolphin.access.RoleUserRelationEntity
import soia.authezat.infra.database.dolphin.base.BaseEntity
import soia.authezat.infra.database.dolphin.session.SessionEntity
import java.util.UUID

@Entity
@Table(name = "users")
class UserEntity(
    @UuidGenerator
    var id: UUID = UUID.randomUUID(),

    @JoinColumn(nullable = false, updatable = false)
    @OneToOne(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY)
    var sign: SignEntity,

    var name: String,

    var email: String,

    @OneToMany(mappedBy = "user", cascade = [(CascadeType.ALL)], orphanRemoval = true)
    var sessions: MutableList<SessionEntity> = mutableListOf(),

    @OneToMany(mappedBy = "user", cascade = [(CascadeType.ALL)], orphanRemoval = true)
    var roleRelations: MutableList<RoleUserRelationEntity> = mutableListOf(),
) :
    BaseEntity()