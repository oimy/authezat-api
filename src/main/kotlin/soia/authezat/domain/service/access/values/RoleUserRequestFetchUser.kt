package soia.authezat.domain.service.access.values

import soia.authezat.domain.service.account.values.User
import soia.authezat.domain.service.base.BaseValue
import soia.authezat.infra.database.dolphin.access.RoleUserRequestEntity
import soia.authezat.infra.database.dolphin.access.enums.RoleUserAccessType
import soia.authezat.infra.database.dolphin.base.enums.RequestStatus
import java.time.LocalDateTime
import java.util.*

data class RoleUserRequestFetchUser(
    val roleSrl: Long,
    val user: User,
    val accessType: RoleUserAccessType,
    val content: String,
    val status: RequestStatus,
    val reason: String?,

    override val srl: Long,
    override val createdBy: UUID,
    override val createdAt: LocalDateTime,
    override val modifiedBy: UUID,
    override val modifiedAt: LocalDateTime,
) :
    BaseValue {

    constructor(request: RoleUserRequestEntity) : this(
        roleSrl = request.role.srl,
        user = User(request.user),
        accessType = request.accessType,
        content = request.content,
        status = request.status,
        reason = request.reason,
        srl = request.srl,
        createdBy = request.createdBy,
        createdAt = request.createdAt,
        modifiedBy = request.modifiedBy,
        modifiedAt = request.modifiedAt
    )

}
