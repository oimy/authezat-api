package soia.authezat.app.controller.access.payloads

import soia.authezat.infra.database.dolphin.access.enums.RoleUserAccessType
import soia.authezat.infra.database.dolphin.base.enums.RequestStatus

data class RoleUserRequestPayload(
    val srl: Long,
    val roleSrl: Long,
    val accessType: RoleUserAccessType,
    val content: String,
    val status: RequestStatus,
    val reason: String?,
)
