package soia.authezat.infra.database.dolphin.access

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import soia.authezat.infra.database.dolphin.account.UserEntity
import soia.authezat.infra.database.dolphin.server.ServerEntity

interface RoleRepository : JpaRepository<RoleEntity, Long> {

    @Query("""
        select count(1) > 0
        from RoleEntity r
        inner join r.userRelations ur
        inner join r.serverRelations sr
        where ur.user = :user 
          and sr.server = :server
    """)
    fun existsByUserAndServer(user: UserEntity, server: ServerEntity): Boolean

    @Query("""
        select count(1) > 0
        from RoleEntity r
        inner join r.userRelations ur
        inner join ur.user u
        inner join u.sign s
        where s.username = :username 
    """)
    fun existsBySrlAndUsername(srl: Long, username: String): Boolean

    @Query("""
        select r
        from RoleEntity r
        inner join r.userRelations ur
        inner join ur.user u
        inner join u.sign s
        where s.username = :username
    """)
    fun findAllByUsername(username: String): List<RoleEntity>

    fun findByName(name: String): RoleEntity?

}