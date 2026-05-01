package soia.authezat.infra.database.dolphin.server

import soia.authezat.answer
import soia.authezat.any
import soia.authezat.capture
import soia.authezat.mock.repository.CrudRepositoryMock
import soia.authezat.whenever

class EndpointRepositoryMock(
    override val repository: EndpointRepository,
    override val entities: Collection<EndpointEntity>
) :
    CrudRepositoryMock<EndpointEntity>(repository, entities) {

    fun findAllByServer() {
        whenever {
            repository.findAllByServer(any())
        } answer { invocation ->
            val capturedServerEntity: ServerEntity = capture<ServerEntity>(position = 0).answer(invocation)
            this.entities.filter { it.server.srl == capturedServerEntity.srl }
        }
    }

}