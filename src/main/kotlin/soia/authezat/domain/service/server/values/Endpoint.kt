package soia.authezat.domain.service.server.values

import soia.authezat.domain.service.access.values.Role
import soia.authezat.domain.service.base.BaseValue
import soia.authezat.infra.database.dolphin.server.EndpointEntity
import soia.authezat.infra.database.dolphin.server.enums.EndpointMethod
import java.time.LocalDateTime
import java.util.*

data class Endpoint(
    val serverSrl: Long,
    val method: EndpointMethod,
    val path: String,
    val roles: List<Role> = emptyList(),

    override val srl: Long,
    override val createdBy: UUID,
    override val createdAt: LocalDateTime,
    override val modifiedBy: UUID,
    override val modifiedAt: LocalDateTime,
) :
    BaseValue {

    constructor(endpoint: EndpointEntity) : this(
        serverSrl = endpoint.server.srl,
        method = endpoint.method,
        path = endpoint.path,
        srl = endpoint.srl,
        createdBy = endpoint.createdBy,
        createdAt = endpoint.createdAt,
        modifiedBy = endpoint.modifiedBy,
        modifiedAt = endpoint.modifiedAt
    )

    constructor(endpoint: EndpointEntity, roles: List<Role>) : this(
        serverSrl = endpoint.server.srl,
        method = endpoint.method,
        path = endpoint.path,
        roles = roles,
        srl = endpoint.srl,
        createdBy = endpoint.createdBy,
        createdAt = endpoint.createdAt,
        modifiedBy = endpoint.modifiedBy,
        modifiedAt = endpoint.modifiedAt
    )

}