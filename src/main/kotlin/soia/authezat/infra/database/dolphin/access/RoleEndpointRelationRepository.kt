package soia.authezat.infra.database.dolphin.access

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface RoleEndpointRelationRepository : JpaRepository<RoleEndpointRelationEntity, Long> {

    @Query("SELECT r FROM RoleEndpointRelationEntity r INNER JOIN FETCH r.role INNER JOIN FETCH r.endpoint")
    fun findAllFetchRoleAndEndpoint(): List<RoleEndpointRelationEntity>

    @Query("SELECT r FROM RoleEndpointRelationEntity r INNER JOIN FETCH r.role INNER JOIN FETCH r.endpoint WHERE r.modifiedAt > :modifiedAt")
    fun findAllByModifiedAtGreaterThanFetchRoleAndEndpoint(modifiedAt: LocalDateTime): List<RoleEndpointRelationEntity>

}