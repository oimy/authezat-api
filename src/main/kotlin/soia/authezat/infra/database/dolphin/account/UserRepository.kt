package soia.authezat.infra.database.dolphin.account

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface UserRepository : JpaRepository<UserEntity, Long> {

    fun findBySignSrl(signSrl: Long): UserEntity?

    @Query(
        """
        select u 
        from UserEntity u 
        left join fetch u.sessions 
        where u.srl = :id
        """
    )
    fun findByIdOrNullFetchSession(id: Long): UserEntity?

    @Query(
        """
        select u 
        from UserEntity u 
        left join fetch u.roleRelations rr 
        left join fetch rr.role 
        where u.srl = :id
        """
    )
    fun findByIdOrNullFetchRole(id: Long): UserEntity?

    @Query("""
        select u 
        from UserEntity u 
        where u.id = :userId
    """)
    fun findByUserId(userId: UUID): UserEntity?

}