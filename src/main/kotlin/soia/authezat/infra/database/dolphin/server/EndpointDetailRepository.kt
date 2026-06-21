package soia.authezat.infra.database.dolphin.server

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface EndpointDetailRepository : JpaRepository<EndpointDetailEntity, Long> {

    @Query(
        """
        SELECT e 
        FROM EndpointDetailEntity e 
        WHERE e.endpoint.srl = :endpointSrl
    """
    )
    fun findByEndpointSrl(endpointSrl: Long): EndpointDetailEntity?

}