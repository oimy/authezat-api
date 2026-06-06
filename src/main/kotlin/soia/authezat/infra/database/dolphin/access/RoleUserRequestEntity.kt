package soia.authezat.infra.database.dolphin.access

import jakarta.persistence.*
import soia.authezat.infra.database.dolphin.access.enums.RoleUserAccessType
import soia.authezat.infra.database.dolphin.account.UserEntity
import soia.authezat.infra.database.dolphin.base.RequestBaseEntity

@Entity
@Table(name = "role_user_requests")
class RoleUserRequestEntity(
    @JoinColumn(nullable = false, updatable = false)
    @ManyToOne(optional = false)
    var role: RoleEntity,

    @JoinColumn(nullable = false, updatable = false)
    @ManyToOne(optional = false)
    var user: UserEntity,

    @Enumerated(value = EnumType.STRING)
    var accessType: RoleUserAccessType,

    var content: String,
) :
    RequestBaseEntity()
