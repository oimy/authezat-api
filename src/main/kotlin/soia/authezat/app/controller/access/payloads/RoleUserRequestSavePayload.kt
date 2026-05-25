package soia.authezat.app.controller.access.payloads

import jakarta.validation.constraints.Max
import soia.authezat.infra.database.dolphin.access.enums.RoleUserAccessType

data class RoleUserRequestSavePayload(
    val accessType: RoleUserAccessType,
    @Max(256)
    val content: String,
)
