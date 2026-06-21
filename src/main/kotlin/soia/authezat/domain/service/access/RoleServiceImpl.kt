package soia.authezat.domain.service.access

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import soia.authezat.domain.service.access.values.Role
import soia.authezat.infra.database.dolphin.access.RoleEntity
import soia.authezat.infra.database.dolphin.access.RoleRepository
import soia.authezat.infra.database.dolphin.access.RoleUserRelationEntity
import soia.authezat.infra.database.dolphin.access.RoleUserRelationRepository
import soia.authezat.infra.database.dolphin.account.UserEntity
import soia.authezat.infra.database.dolphin.account.UserRepository
import java.util.UUID

@Service
class RoleServiceImpl(
    private val roleRepository: RoleRepository,
    private val userRepository: UserRepository,
    private val roleUserRelationRepository: RoleUserRelationRepository,
) :
    RoleService {

    @Transactional
    override fun save(name: String, createdBy: UUID) {
        val user: UserEntity = userRepository.findByUserId(createdBy)
            ?: throw EntityNotFoundException()
        val role = RoleEntity(name = name.uppercase())
        val savedRole: RoleEntity = roleRepository.save(role)
        val roleUserRelation = RoleUserRelationEntity(user = user, role = savedRole)
        roleUserRelationRepository.save(roleUserRelation)
    }

    @Transactional(readOnly = true)
    override fun findAll(): List<Role> =
        roleRepository.findAll().map { Role(role = it) }

    @Transactional(readOnly = true)
    override fun findAllByUserId(userId: UUID): List<Role> =
        roleRepository.findAllByUserId(userId).map { Role(role = it) }

}