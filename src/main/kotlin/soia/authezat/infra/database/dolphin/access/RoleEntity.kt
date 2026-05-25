package soia.authezat.infra.database.dolphin.access

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import soia.authezat.infra.database.dolphin.base.BaseEntity


@Entity
@Table(name = "roles")
class RoleEntity(
    var name: String,

    @OneToMany(mappedBy = "role", cascade = [CascadeType.ALL], orphanRemoval = true)
    var endpointRelations: MutableList<RoleEndpointRelationEntity> = mutableListOf(),

    @OneToMany(mappedBy = "role", cascade = [CascadeType.ALL], orphanRemoval = true)
    var userRelations: MutableList<RoleUserRelationEntity> = mutableListOf(),

    @OneToMany(mappedBy = "role", cascade = [CascadeType.ALL], orphanRemoval = true)
    var serverRelations: MutableList<RoleServerRelationEntity> = mutableListOf(),
) :
    BaseEntity()