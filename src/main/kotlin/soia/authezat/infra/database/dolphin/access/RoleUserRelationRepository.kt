package soia.authezat.infra.database.dolphin.access

import org.springframework.data.jpa.repository.JpaRepository

interface RoleUserRelationRepository : JpaRepository<RoleUserRelationEntity, Long>