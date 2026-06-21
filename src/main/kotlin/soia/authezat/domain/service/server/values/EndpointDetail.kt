package soia.authezat.domain.service.server.values

import soia.authezat.domain.service.base.BaseValue
import soia.authezat.infra.database.dolphin.server.EndpointDetailEntity
import java.time.LocalDateTime
import java.util.*

data class EndpointDetail(
    val operationId: String,
    val summary: String?,
    val description: String?,
    val tags: List<String>,
    val variables: List<Map<String, Any>>,
    val parameters: List<Map<String, Any>>,
    val requestBodies: List<Map<String, Any>>,
    val responseBodies: List<Map<String, Any>>,

    override val srl: Long,
    override val createdBy: UUID,
    override val createdAt: LocalDateTime,
    override val modifiedBy: UUID,
    override val modifiedAt: LocalDateTime,
) :
    BaseValue {

    constructor(detail: EndpointDetailEntity) : this(
        operationId = detail.operationId,
        summary = detail.summary,
        description = detail.description,
        tags = detail.tags,
        variables = detail.variables,
        parameters = detail.parameters,
        requestBodies = detail.requestBodies,
        responseBodies = detail.responseBodies,
        srl = detail.srl,
        createdBy = detail.createdBy,
        createdAt = detail.createdAt,
        modifiedBy = detail.modifiedBy,
        modifiedAt = detail.modifiedAt,
    )

}
