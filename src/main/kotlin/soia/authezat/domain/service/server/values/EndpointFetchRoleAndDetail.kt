package soia.authezat.domain.service.server.values

import soia.authezat.domain.service.access.values.Role
import soia.authezat.domain.service.base.BaseValue
import soia.authezat.infra.database.dolphin.server.EndpointEntity
import soia.authezat.infra.database.dolphin.server.enums.EndpointMethod
import java.time.LocalDateTime
import java.util.*

data class EndpointFetchRoleAndDetail(
    val serverSrl: Long,
    val method: EndpointMethod,
    val path: String,
    val roles: List<Role> = emptyList(),
    val detail: EndpointDetail,

    override val srl: Long,
    override val createdBy: UUID,
    override val createdAt: LocalDateTime,
    override val modifiedBy: UUID,
    override val modifiedAt: LocalDateTime,
) :
    BaseValue {

    constructor(endpoint: EndpointEntity, roles: List<Role>, detail: EndpointDetail) : this(
        serverSrl = endpoint.server.srl,
        method = endpoint.method,
        path = endpoint.path,
        roles = roles,
        detail = detail,
        srl = endpoint.srl,
        createdBy = endpoint.createdBy,
        createdAt = endpoint.createdAt,
        modifiedBy = endpoint.modifiedBy,
        modifiedAt = endpoint.modifiedAt
    )

}