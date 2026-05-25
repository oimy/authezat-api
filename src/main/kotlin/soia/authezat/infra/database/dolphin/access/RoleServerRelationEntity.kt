package soia.authezat.infra.database.dolphin.access;

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import soia.authezat.infra.database.dolphin.base.ImmutableBaseEntity
import soia.authezat.infra.database.dolphin.server.ServerEntity

@Entity
@Table(name = "role_server_relations")
class RoleServerRelationEntity(
    @JoinColumn(nullable = false, updatable = false)
    @ManyToOne(optional = false)
    var role: RoleEntity,

    @JoinColumn(nullable = false, updatable = false)
    @ManyToOne(optional = false)
    var server: ServerEntity,
) :
    ImmutableBaseEntity()