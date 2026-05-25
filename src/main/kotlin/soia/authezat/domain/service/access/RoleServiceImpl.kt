package soia.authezat.domain.service.access

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import soia.authezat.domain.service.access.values.Role
import soia.authezat.infra.database.dolphin.access.RoleEntity
import soia.authezat.infra.database.dolphin.access.RoleRepository
import soia.authezat.infra.database.dolphin.access.RoleUserRelationEntity
import soia.authezat.infra.database.dolphin.access.RoleUserRelationRepository
import soia.authezat.infra.database.dolphin.account.UserRepository

@Service
class RoleServiceImpl(
    private val roleRepository: RoleRepository,
    private val userRepository: UserRepository,
    private val roleUserRelationRepository: RoleUserRelationRepository,
) :
    RoleService {

    @Transactional
    override fun save(name: String, createdBy: String) {
        val user = userRepository.findByUsername(createdBy)
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
    override fun findAll(accessedBy: String): List<Role> =
        roleRepository.findAllByUsername(username = accessedBy).map { Role(role = it) }

}