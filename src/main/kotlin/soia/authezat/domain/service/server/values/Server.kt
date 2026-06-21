package soia.authezat.domain.service.server.values

import soia.authezat.domain.service.base.BaseValue
import soia.authezat.infra.database.dolphin.server.ServerEntity
import java.time.LocalDateTime
import java.util.*

data class Server(
    val name: String,
    val url: String,
    val version: Short,

    override val srl: Long,
    override val createdBy: UUID,
    override val createdAt: LocalDateTime,
    override val modifiedBy: UUID,
    override val modifiedAt: LocalDateTime,
) :
    BaseValue {

    constructor(server: ServerEntity) : this(
        name = server.name,
        url = server.url,
        version = server.version,
        srl = server.srl,
        createdBy = server.createdBy,
        createdAt = server.createdAt,
        modifiedBy = server.modifiedBy,
        modifiedAt = server.modifiedAt
    )

}