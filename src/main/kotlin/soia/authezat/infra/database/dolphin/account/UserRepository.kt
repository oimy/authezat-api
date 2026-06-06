package soia.authezat.infra.database.dolphin.account

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<UserEntity, Long> {

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

    @Query(
        """
        select u
        from UserEntity u
        inner join u.sign s
        where s.username = :username
    """
    )
    fun findByUsername(username: String): UserEntity?

}