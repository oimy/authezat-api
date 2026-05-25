package soia.authezat.infra.database.dolphin.server

import jakarta.persistence.*
import soia.authezat.infra.database.dolphin.access.RoleEndpointRelationEntity
import soia.authezat.infra.database.dolphin.access.RoleEntity
import soia.authezat.infra.database.dolphin.access.RoleServerRelationEntity
import soia.authezat.infra.database.dolphin.base.BaseEntity

@Entity
@Table(name = "servers")
class ServerEntity(
    var name: String,

    var url: String,

    var version: Short,

    @OneToMany(mappedBy = "server", cascade = [(CascadeType.ALL)], orphanRemoval = true)
    var roleRelations: MutableList<RoleServerRelationEntity> = mutableListOf(),
) :
    BaseEntity() {

    @OneToMany(mappedBy = "server", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    var endpoints: MutableList<EndpointEntity> = mutableListOf()

}