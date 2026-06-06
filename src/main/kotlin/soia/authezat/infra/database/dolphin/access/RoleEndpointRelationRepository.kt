package soia.authezat.infra.database.dolphin.access

import org.springframework.data.jpa.repository.JpaRepository

interface RoleEndpointRelationRepository : JpaRepository<RoleEndpointRelationEntity, Long> {

    fun findByRoleSrlAndEndpointSrl(roleSrl: Long, endpointSrl: Long): RoleEndpointRelationEntity?

}