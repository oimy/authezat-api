package soia.authezat.domain.service.access

import soia.authezat.domain.service.access.values.RoleUserRequest
import soia.authezat.domain.service.access.values.RoleUserRequestFetchUser
import soia.authezat.infra.database.dolphin.access.enums.RoleUserAccessType
import soia.authezat.infra.database.dolphin.base.enums.RequestStatus

interface RoleUserRequestService {

    fun save(roleSrl: Long, accessType: RoleUserAccessType, content: String, createdBy: String)

    fun findAllByStatusInAndCreatedBy(statuses: Collection<RequestStatus>, accessedBy: String): List<RoleUserRequest>

    fun findAllByRoleInAndStatusFetchUser(status: RequestStatus, accessedBy: String): List<RoleUserRequestFetchUser>

    fun accept(requestSrl: Long, acceptedBy: String)

    fun reject(requestSrl: Long, reason: String, rejectedBy: String)

    fun delete(requestSrl: Long, deletedBy: String)

}