package soia.authezat.mock.repository

import org.springframework.data.repository.Repository
import soia.authezat.infra.database.dolphin.base.Entity

interface RepositoryMock<T : Entity, ID : Any> {

    val repository: Repository<T, ID>

    val entities: Collection<T>

}