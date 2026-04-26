package soia.authezat.domain.service.access

import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import soia.authezat.domain.service.access.values.RoleEndpoint
import soia.authezat.infra.database.dolphin.access.RoleEndpointRelationEntity
import soia.authezat.infra.database.dolphin.access.RoleEndpointRelationRepository
import soia.authezat.infra.database.dolphin.access.RoleEntity
import soia.authezat.infra.database.dolphin.access.RoleRepository
import soia.authezat.infra.database.dolphin.server.EndpointEntity
import soia.authezat.infra.database.dolphin.server.EndpointRepository
import java.time.LocalDateTime

@Service(value = "accessRoleEndpointService")
class RoleEndpointServiceImpl(
    private val roleEndpointRelationRepository: RoleEndpointRelationRepository,
    private val roleRepository: RoleRepository,
    private val endpointRepository: EndpointRepository,
) : RoleEndpointService {

    override fun save(roleSrl: Long, endpointSrl: Long) {
        val role: RoleEntity = roleRepository.findByIdOrNull(roleSrl)
            ?: throw EntityNotFoundException("role")
        val endpoint: EndpointEntity = endpointRepository.findByIdOrNull(endpointSrl)
            ?: throw EntityNotFoundException("endpoint")
        val relation = RoleEndpointRelationEntity(role = role, endpoint = endpoint)

        roleEndpointRelationRepository.save(relation)
    }

    override fun findAll(): List<RoleEndpoint> =
        roleEndpointRelationRepository.findAllFetchRoleAndEndpoint()
            .map { RoleEndpoint(it) }

    override fun findByModifiedAtGreaterThan(modifiedAt: LocalDateTime): List<RoleEndpoint> =
        roleEndpointRelationRepository.findAllByModifiedAtGreaterThanFetchRoleAndEndpoint(modifiedAt = modifiedAt)
            .map { RoleEndpoint(it) }

}