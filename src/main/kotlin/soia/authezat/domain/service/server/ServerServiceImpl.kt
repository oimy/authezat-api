package soia.authezat.domain.service.server

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import soia.authezat.domain.service.server.values.Server
import soia.authezat.infra.database.dolphin.access.*
import soia.authezat.infra.database.dolphin.account.UserEntity
import soia.authezat.infra.database.dolphin.account.UserRepository
import soia.authezat.infra.database.dolphin.server.ServerEntity
import soia.authezat.infra.database.dolphin.server.ServerRepository
import java.util.*

@Service(value = "serverService")
class ServerServiceImpl(
    private val serverRepository: ServerRepository,
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val roleServerRelationRepository: RoleServerRelationRepository,
    private val roleUserRelationRepository: RoleUserRelationRepository,
) :
    ServerService {

    @Transactional
    override fun save(name: String, url: String, version: Short, createdBy: UUID) {
        val user: UserEntity = userRepository.findByUserId(createdBy)
            ?: throw EntityNotFoundException("user not found")
        val serverRoleName = name.uppercase() + "_SERVER"
        val roleEntity = RoleEntity(name = serverRoleName)
        val savedRoleEntity: RoleEntity = roleRepository.save(roleEntity)

        val serverEntity = ServerEntity(name = name, url = url, version = version)
        val savedServerEntity: ServerEntity = serverRepository.save(serverEntity)

        val roleServerRelationEntity = RoleServerRelationEntity(server = savedServerEntity, role = savedRoleEntity)
        roleServerRelationRepository.save(roleServerRelationEntity)

        val roleUserRelationEntity = RoleUserRelationEntity(user = user, role = savedRoleEntity)
        roleUserRelationRepository.save(roleUserRelationEntity)
    }

    @Transactional(readOnly = true)
    override fun findAll(): List<Server> =
        serverRepository.findAll().map { Server(server = it) }

    @Transactional(readOnly = true)
    override fun findAllByUserId(userId: UUID): List<Server> =
        serverRepository.findAllByUserId(userId).map { Server(server = it) }

}