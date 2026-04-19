package soia.authezat.infra.database.dolphin.server

import jakarta.persistence.*
import soia.authezat.infra.database.dolphin.base.BaseEntity

@Entity
@Table(name = "servers")
class ServerEntity(
    var name: String,

    var url: String,

    var version: Short,
) :
    BaseEntity() {

    @OneToMany(mappedBy = "server", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    var endpoints: MutableList<EndpointEntity> = mutableListOf()

}