package soia.authezat.domain.service.server

import org.springframework.stereotype.Service
import soia.authezat.domain.service.server.values.Server
import soia.authezat.infra.database.dolphin.server.ServerRepository

@Service(value = "serverService")
class ServerServiceImpl(
    private val serverRepository: ServerRepository,
) :
    ServerService {

    override fun findAll(): List<Server> =
        serverRepository.findAll().map { Server(server = it) }

}