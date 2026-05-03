package soia.authezat.mock.repository

import org.springframework.data.repository.CrudRepository
import soia.authezat.answer
import soia.authezat.any
import soia.authezat.anyCollection
import soia.authezat.capture
import soia.authezat.captureFirst
import soia.authezat.infra.database.dolphin.base.Entity
import soia.authezat.whenever
import java.util.Optional

abstract class CrudRepositoryMock<T : Entity>(
    override val repository: CrudRepository<T, Long>,
    override val entities: Collection<T>
) :
    RepositoryMock<T, Long> {

    fun save() {
        whenever {
            repository.save(any())
        } answer { invocation ->
            val capturedEntity: T = capture<T>(position = 0).answer(invocation)
            this.entities.first { capturedEntity.srl == it.srl }
        }
    }

    fun saveAll() {
        whenever {
            repository.saveAll(anyCollection())
        } answer { invocation ->
            val capturedEntities: Collection<T> = captureFirst<Collection<T>>().answer(invocation)
            val capturedSrls = capturedEntities.map { it.srl }
            this.entities.filter { capturedSrls.contains(it.srl) }
        }
    }

    fun findById() {
        whenever {
            repository.findById(any())
        } answer { invocation ->
            val capturedSrl: Long = captureFirst<Long>().answer(invocation)
            this.entities.firstOrNull { capturedSrl == it.srl }
                ?.let { Optional.of(it) }
                ?: Optional.empty()
        }
    }

    fun findAll() {
        whenever {
            repository.findAll()
        } answer {
            this.entities
        }
    }

}