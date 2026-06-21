package soia.authezat.domain.service.account.values

import soia.authezat.domain.service.base.BaseValue
import soia.authezat.infra.database.dolphin.account.UserEntity
import java.time.LocalDateTime
import java.util.*

data class User(
    val id: UUID,
    val name: String,
    val email: String,

    override val srl: Long,
    override val createdBy: UUID,
    override val createdAt: LocalDateTime,
    override val modifiedBy: UUID,
    override val modifiedAt: LocalDateTime,
) :
    BaseValue {

    constructor(user: UserEntity) : this(
        id = user.id,
        name = user.name,
        email = user.email,
        srl = user.srl,
        createdBy = user.createdBy,
        createdAt = user.createdAt,
        modifiedBy = user.modifiedBy,
        modifiedAt = user.modifiedAt
    )

}