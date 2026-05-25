package soia.authezat.infra.database.dolphin.server

import jakarta.persistence.*
import org.hibernate.annotations.JdbcType
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import soia.authezat.infra.database.dolphin.access.RoleEndpointRelationEntity
import soia.authezat.infra.database.dolphin.base.BaseEntity
import soia.authezat.infra.database.dolphin.server.enums.EndpointMethod

@Entity
@Table(name = "endpoints")
class EndpointEntity(
    @JoinColumn(nullable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    var server: ServerEntity,

    @Enumerated(value = EnumType.STRING)
    var method: EndpointMethod,

    var path: String,

    @JdbcTypeCode(SqlTypes.JSON)
    var content: Map<String, Any> = mutableMapOf(),

    @OneToMany(mappedBy = "endpoint", cascade = [(CascadeType.ALL)], orphanRemoval = true)
    var roleRelations: MutableList<RoleEndpointRelationEntity> = mutableListOf(),
) :
    BaseEntity()