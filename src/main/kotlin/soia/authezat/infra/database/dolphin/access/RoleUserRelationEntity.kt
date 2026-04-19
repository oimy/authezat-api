package soia.authezat.infra.database.dolphin.access

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import soia.authezat.infra.database.dolphin.account.UserEntity
import soia.authezat.infra.database.dolphin.base.BaseEntity
import soia.authezat.infra.database.dolphin.server.EndpointEntity


@Entity
@Table(name = "role_user_relations")
class RoleUserRelationEntity(
    @JoinColumn(nullable = false, updatable = false)
    @ManyToOne(optional = false)
    var role: RoleEntity,

    @JoinColumn(nullable = false, updatable = false)
    @ManyToOne(optional = false)
    var user: UserEntity,
) :
    BaseEntity()