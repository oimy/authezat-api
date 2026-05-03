package soia.authezat.domain.service.server

import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import soia.authezat.domain.service.access.values.Role
import soia.authezat.domain.service.server.values.Endpoint
import soia.authezat.infra.database.dolphin.server.EndpointEntity
import soia.authezat.infra.database.dolphin.server.EndpointRepository
import soia.authezat.infra.database.dolphin.server.ServerEntity
import soia.authezat.infra.database.dolphin.server.ServerRepository
import soia.authezat.infra.database.dolphin.server.enums.EndpointMethod
import java.time.LocalDateTime

@Service(value = "serverEndpointService")
class EndpointServiceImpl(
    private val endpointRepository: EndpointRepository,
    private val serverRepository: ServerRepository,
) :
    EndpointService {

    @Transactional
    override fun save(serverSrl: Long, method: EndpointMethod, path: String) {
        val serverEntity: ServerEntity = serverRepository.findByIdOrNull(serverSrl)
            ?: throw EntityNotFoundException()
        val endpointEntity = EndpointEntity(server = serverEntity, method = method, path = path)

        endpointRepository.save(endpointEntity)
    }

    @Transactional(readOnly = true)
    override fun findAllByServerSrl(serverSrl: Long): List<Endpoint> {
        val server: ServerEntity = serverRepository.findByIdOrNull(serverSrl)
            ?: throw EntityNotFoundException()

        return endpointRepository.findAllByServer(server).map { Endpoint(endpoint = it) }
    }

    @Transactional(readOnly = true)
    override fun findAllByModifiedAtGreaterThenFetchRole(afterModifiedAt: LocalDateTime): List<Endpoint> =
        endpointRepository.findAllByModifiedAtGreaterThenFetchRole(afterModifiedAt)
            .map { endpoint ->
                val roles: List<Role> = endpoint.roleRelations.map { Role(role = it.role) }
                Endpoint(endpoint = endpoint, roles = roles)
            }

}