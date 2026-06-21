package soia.authezat.domain.service.server

import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import soia.authezat.app.controller.server.payloads.EndpointSavePayload
import soia.authezat.domain.service.access.values.Role
import soia.authezat.domain.service.server.values.Endpoint
import soia.authezat.infra.database.dolphin.access.RoleEndpointRelationEntity
import soia.authezat.infra.database.dolphin.access.RoleEndpointRelationRepository
import soia.authezat.infra.database.dolphin.access.RoleEntity
import soia.authezat.infra.database.dolphin.access.RoleRepository
import soia.authezat.infra.database.dolphin.account.UserEntity
import soia.authezat.infra.database.dolphin.account.UserRepository
import soia.authezat.infra.database.dolphin.server.*
import java.time.LocalDateTime
import java.util.*

@Service(value = "serverEndpointService")
class EndpointServiceImpl(
    private val endpointRepository: EndpointRepository,
    private val serverRepository: ServerRepository,
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val roleEndpointRelationRepository: RoleEndpointRelationRepository,
) :
    EndpointService {

    @Transactional
    override fun saveAll(serverSrl: Long, endpointSaves: List<EndpointSavePayload>, createdBy: UUID) {
        val userEntity: UserEntity = userRepository.findByUserId(createdBy)
            ?: throw EntityNotFoundException()
        val serverEntity: ServerEntity = serverRepository.findByIdOrNull(serverSrl)
            ?: throw EntityNotFoundException()
        require(roleRepository.existsByUserAndServer(user = userEntity, server = serverEntity))

        val endpointEntities: List<EndpointEntity> = endpointSaves
            .map {
                val endpointEntity = EndpointEntity(server = serverEntity, method = it.method, path = it.path)
                endpointEntity.detail = EndpointDetailEntity(
                    endpoint = endpointEntity,
                    tags = it.detail.tags,
                    operationId = it.detail.operationId,
                    summary = it.detail.summary,
                    description = it.detail.description,
                    variables = it.detail.variables,
                    parameters = it.detail.parameters,
                    requestBodies = it.detail.requestBodies,
                    responseBodies = it.detail.responseBodies
                )
                endpointEntity
            }
        endpointRepository.saveAll(endpointEntities)
    }

    @Transactional(readOnly = true)
    override fun findAllByServerSrl(serverSrl: Long): List<Endpoint> {
        val serverEntity: ServerEntity = serverRepository.findByIdOrNull(serverSrl)
            ?: throw EntityNotFoundException()

        return endpointRepository.findAllByServer(serverEntity).map { Endpoint(endpoint = it) }
    }

    @Transactional(readOnly = true)
    override fun findAllByServerSrlAndUserId(serverSrl: Long, userId: UUID): List<Endpoint> {
        val userEntity: UserEntity = userRepository.findByUserId(userId)
            ?: throw EntityNotFoundException()
        val serverEntity: ServerEntity = serverRepository.findByIdOrNull(serverSrl)
            ?: throw EntityNotFoundException()
        require(roleRepository.existsByUserAndServer(user = userEntity, server = serverEntity))

        return endpointRepository.findAllByServer(serverEntity).map { Endpoint(endpoint = it) }
    }

    @Transactional(readOnly = true)
    override fun findAllByModifiedAtGreaterThenFetchRole(afterModifiedAt: LocalDateTime): List<Endpoint> =
        endpointRepository.findAllByModifiedAtGreaterThenFetchRole(afterModifiedAt)
            .map { endpoint ->
                val roles: List<Role> = endpoint.roleRelations.map { Role(role = it.role) }
                Endpoint(endpoint = endpoint, roles = roles)
            }

    @Transactional(readOnly = true)
    override fun findBySrlFetchRole(srl: Long): Endpoint =
        endpointRepository.findByIdOrNullFetchRole(srl)
            ?.let { endpoint ->
                val roles: List<Role> = endpoint.roleRelations.map { Role(role = it.role) }
                Endpoint(endpoint = endpoint, roles = roles)
            }
            ?: throw EntityNotFoundException()

    @Transactional
    override fun addRole(srl: Long, roleSrl: Long, addedBy: UUID) {
        require(roleRepository.existsBySrlAndUserId(srl = roleSrl, userId = addedBy))

        val endpointEntity: EndpointEntity = endpointRepository.findByIdOrNull(srl)
            ?: throw EntityNotFoundException()
        val roleEntity: RoleEntity = roleRepository.findByIdOrNull(roleSrl)
            ?: throw EntityNotFoundException()
        val relationEntity = RoleEndpointRelationEntity(role = roleEntity, endpoint = endpointEntity)
        roleEndpointRelationRepository.save(relationEntity)
    }

    @Transactional
    override fun removeRole(srl: Long, roleSrl: Long, removedBy: UUID) {
        require(roleRepository.existsBySrlAndUserId(srl = roleSrl, userId = removedBy))

        val relationEntity: RoleEndpointRelationEntity = roleEndpointRelationRepository
            .findByRoleSrlAndEndpointSrl(roleSrl = roleSrl, endpointSrl = srl)
            ?: throw EntityNotFoundException()
        roleEndpointRelationRepository.delete(relationEntity)
    }

}