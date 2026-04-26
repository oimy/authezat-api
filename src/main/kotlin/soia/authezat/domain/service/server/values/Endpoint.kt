package soia.authezat.domain.service.server.values

import soia.authezat.domain.service.base.BaseValue
import soia.authezat.infra.database.dolphin.server.EndpointEntity
import soia.authezat.infra.database.dolphin.server.enums.EndpointMethod
import java.time.LocalDateTime

data class Endpoint(
    val serverSrl: Long,
    val method: EndpointMethod,
    val path: String,

    override val srl: Long,
    override val createdBy: String,
    override val createdAt: LocalDateTime,
    override val modifiedBy: String,
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

}