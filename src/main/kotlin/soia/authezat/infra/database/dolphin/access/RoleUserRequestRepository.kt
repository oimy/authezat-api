package soia.authezat.infra.database.dolphin.access

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import soia.authezat.infra.database.dolphin.base.enums.RequestStatus
import java.util.*

interface RoleUserRequestRepository : JpaRepository<RoleUserRequestEntity, Long> {

    fun findAllByStatusInAndCreatedBy(statuses: Collection<RequestStatus>, createdBy: UUID): List<RoleUserRequestEntity>

    @Query(
        """
        select q
        from RoleUserRequestEntity q
        inner join fetch q.user
        where q.status = :status 
          and q.role in :roles
    """
    )
    fun findAllByStatusAndRoleInFetchUser(
        status: RequestStatus,
        roles: Collection<RoleEntity>,
    ): List<RoleUserRequestEntity>

}