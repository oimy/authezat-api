package soia.authezat.domain.service.access

import soia.authezat.domain.service.access.values.RoleUserRequest
import soia.authezat.domain.service.access.values.RoleUserRequestFetchUser
import soia.authezat.infra.database.dolphin.access.enums.RoleUserAccessType
import soia.authezat.infra.database.dolphin.base.enums.RequestStatus
import java.util.UUID

interface RoleUserRequestService {

    fun save(roleSrl: Long, accessType: RoleUserAccessType, content: String, createdBy: UUID)

    fun findAllByStatusInAndCreatedBy(statuses: Collection<RequestStatus>, createdBy: UUID): List<RoleUserRequest>

    fun findAllByRoleInAndStatusFetchUser(status: RequestStatus, accessedBy: UUID): List<RoleUserRequestFetchUser>

    fun accept(requestSrl: Long, acceptedBy: UUID)

    fun reject(requestSrl: Long, reason: String, rejectedBy: UUID)

    fun delete(requestSrl: Long, deletedBy: UUID)

}