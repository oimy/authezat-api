package soia.authezat.infra.database.dolphin.server

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import soia.authezat.infra.database.dolphin.base.BaseEntity
import soia.authezat.infra.database.dolphin.server.enums.EndpointMethod

@Entity
@Table(name = "servers")
class EndpointEntity(
    @JoinColumn(nullable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    var server: ServerEntity,

    @Enumerated(value = EnumType.STRING)
    var method: EndpointMethod,

    var path: String,
) :
    BaseEntity()