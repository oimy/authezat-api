package soia.authezat.infra.database.dolphin.server

import soia.authezat.mock.repository.CrudRepositoryMock

class ServerRepositoryMock(
    override val repository: ServerRepository,
    override val entities: Collection<ServerEntity>
) :
    CrudRepositoryMock<ServerEntity>(repository, entities)