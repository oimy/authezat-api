package soia.authezat.domain.service.access

import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import soia.authezat.domain.service.access.values.RoleUserRequest
import soia.authezat.domain.service.access.values.RoleUserRequestFetchUser
import soia.authezat.infra.database.dolphin.access.*
import soia.authezat.infra.database.dolphin.access.enums.RoleUserAccessType
import soia.authezat.infra.database.dolphin.account.UserEntity
import soia.authezat.infra.database.dolphin.account.UserRepository
import soia.authezat.infra.database.dolphin.base.enums.RequestStatus
import java.util.*

@Service
class RoleUserRequestServiceImpl(
    private val roleUserRequestRepository: RoleUserRequestRepository,
    private val roleRepository: RoleRepository,
    private val userRepository: UserRepository,
    private val roleUserRelationRepository: RoleUserRelationRepository,
) :
    RoleUserRequestService {

    @Transactional
    override fun save(roleSrl: Long, accessType: RoleUserAccessType, content: String, createdBy: UUID) {
        val roleEntity: RoleEntity = roleRepository.findByIdOrNull(roleSrl)
            ?: throw EntityNotFoundException()
        val userEntity: UserEntity = userRepository.findByUserId(createdBy)
            ?: throw EntityNotFoundException()
        val requestEntity = RoleUserRequestEntity(
            role = roleEntity,
            user = userEntity,
            accessType = accessType,
            content = content
        )
        roleUserRequestRepository.save(requestEntity)
    }

    @Transactional(readOnly = true)
    override fun findAllByStatusInAndCreatedBy(
        statuses: Collection<RequestStatus>,
        createdBy: UUID,
    ): List<RoleUserRequest> =
        roleUserRequestRepository.findAllByStatusInAndCreatedBy(statuses = statuses, createdBy = createdBy)
            .map { RoleUserRequest(it) }

    @Transactional(readOnly = true)
    override fun findAllByRoleInAndStatusFetchUser(
        status: RequestStatus,
        accessedBy: UUID,
    ): List<RoleUserRequestFetchUser> {
        val roleEntities: List<RoleEntity> = roleRepository.findAllByUserId(accessedBy)
        if (roleEntities.isEmpty()) {
            return emptyList()
        }

        return roleUserRequestRepository.findAllByStatusAndRoleInFetchUser(status = status, roles = roleEntities)
            .map { RoleUserRequestFetchUser(it) }
    }

    @Transactional
    override fun accept(requestSrl: Long, acceptedBy: UUID) {
        val requestEntity = roleUserRequestRepository.findByIdOrNull(requestSrl)
            ?: throw EntityNotFoundException()
        require(roleRepository.existsBySrlAndUserId(srl = requestEntity.role.srl, userId = acceptedBy))

        val roleUserRelationEntity = RoleUserRelationEntity(role = requestEntity.role, user = requestEntity.user)
        roleUserRelationRepository.save(roleUserRelationEntity)
        requestEntity.accept()
    }

    @Transactional
    override fun reject(requestSrl: Long, reason: String, rejectedBy: UUID) {
        val requestEntity = roleUserRequestRepository.findByIdOrNull(requestSrl)
            ?: throw EntityNotFoundException()
        require(roleRepository.existsBySrlAndUserId(srl = requestEntity.role.srl, userId = rejectedBy))

        requestEntity.reject(reason)
    }

    @Transactional
    override fun delete(requestSrl: Long, deletedBy: UUID) {
        val requestEntity = roleUserRequestRepository.findByIdOrNull(requestSrl)
            ?: throw EntityNotFoundException()
        require(roleRepository.existsBySrlAndUserId(srl = requestEntity.role.srl, userId = deletedBy))
        require(requestEntity.status != RequestStatus.APPROVE) { "can't delete request which is already approved" }

        roleUserRequestRepository.delete(requestEntity)
    }
}