package soia.authezat.infra.database.dolphin.account

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<UserEntity, Long> {

    @Query("select u from UserEntity u LEFT JOIN FETCH u.sessions where u.srl = :id")
    fun findByIdOrNullFetchSession(id: Long): UserEntity?

}