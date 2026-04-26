package soia.authezat.domain.service.server

import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import soia.authezat.domain.service.server.values.Endpoint
import soia.authezat.infra.database.dolphin.server.EndpointRepository
import soia.authezat.infra.database.dolphin.server.ServerEntity
import soia.authezat.infra.database.dolphin.server.ServerRepository

@Service(value = "serverEndpointService")
class EndpointServiceImpl(
    private val endpointRepository: EndpointRepository,
    private val serverRepository: ServerRepository,
) :
    EndpointService {

    override fun findAllByServerSrl(serverSrl: Long): List<Endpoint> {
        val server: ServerEntity = serverRepository.findByIdOrNull(serverSrl)
            ?: throw EntityNotFoundException()

        return endpointRepository.findAllByServer(server).map { Endpoint(endpoint = it) }
    }

}