package soia.authezat.domain.service.access.values

import soia.authezat.domain.service.base.BaseValue
import soia.authezat.infra.database.dolphin.access.RoleEntity
import java.time.LocalDateTime
import java.util.*

data class Role(
    val name: String,

    override val srl: Long,
    override val createdBy: UUID,
    override val createdAt: LocalDateTime,
    override val modifiedBy: UUID,
    override val modifiedAt: LocalDateTime,
) :
    BaseValue {

    constructor(role: RoleEntity) : this(
        name = role.name,
        srl = role.srl,
        createdBy = role.createdBy,
        createdAt = role.createdAt,
        modifiedBy = role.modifiedBy,
        modifiedAt = role.modifiedAt
    )

}