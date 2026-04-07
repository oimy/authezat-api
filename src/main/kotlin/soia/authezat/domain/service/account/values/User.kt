package soia.authezat.domain.service.account.values

import soia.authezat.domain.service.base.BaseValue
import soia.authezat.infra.database.dolphin.account.UserEntity
import java.time.LocalDateTime

data class User(
    val name: String,
    val email: String,

    override val srl: Long,
    override val createdBy: String,
    override val createdAt: LocalDateTime,
    override val modifiedBy: String,
    override val modifiedAt: LocalDateTime,
) :
    BaseValue {

    constructor(user: UserEntity) : this(
        name = user.name,
        email = user.email,
        srl = user.srl,
        createdBy = user.createdBy,
        createdAt = user.createdAt,
        modifiedBy = user.modifiedBy,
        modifiedAt = user.modifiedAt
    )

}