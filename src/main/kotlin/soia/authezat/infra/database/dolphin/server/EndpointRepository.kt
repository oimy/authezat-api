package soia.authezat.infra.database.dolphin.server

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface EndpointRepository : JpaRepository<EndpointEntity, Long> {

    fun findAllByServer(server: ServerEntity): List<EndpointEntity>

    @Query(
        """
        select e
        from EndpointEntity e
        left join fetch e.roleRelations rr 
        left join fetch rr.role
        where e.modifiedAt > :modifiedAt
    """
    )
    fun findAllByModifiedAtGreaterThenFetchRole(modifiedAt: LocalDateTime): List<EndpointEntity>

    @Query(
        """
        select e
        from EndpointEntity e
        left join fetch e.roleRelations rr 
        left join fetch rr.role
        where e.srl = :srl
    """
    )
    fun findByIdOrNullFetchRole(srl: Long): EndpointEntity?

}