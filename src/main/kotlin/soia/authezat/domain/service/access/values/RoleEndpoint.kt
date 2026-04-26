package soia.authezat.domain.service.access.values

import soia.authezat.domain.service.base.BaseValue
import soia.authezat.domain.service.server.values.Endpoint
import soia.authezat.infra.database.dolphin.access.RoleEndpointRelationEntity
import java.time.LocalDateTime

data class RoleEndpoint(
    val role: Role,
    val endpoint: Endpoint,

    override val srl: Long,
    override val createdBy: String,
    override val createdAt: LocalDateTime,
    override val modifiedBy: String,
    override val modifiedAt: LocalDateTime,
) :
    BaseValue {

    constructor(relation: RoleEndpointRelationEntity) : this(
        role = Role(role = relation.role),
        endpoint = Endpoint(endpoint = relation.endpoint),
        srl = relation.srl,
        createdBy = relation.createdBy,
        createdAt = relation.createdAt,
        modifiedBy = relation.modifiedBy,
        modifiedAt = relation.modifiedAt
    )

}