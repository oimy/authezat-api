package soia.authezat.infra.database.dolphin.server

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface ServerRepository : JpaRepository<ServerEntity, Long> {

    @Query(
        """
        select s
        from ServerEntity s
        inner join s.roleRelations re
        inner join re.role r
        inner join r.userRelations ur
        inner join ur.user u
        where u.id = :userId
    """
    )
    fun findAllByUserId(userId: UUID): List<ServerEntity>

}