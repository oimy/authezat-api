package soia.authezat.infra.database.dolphin.account

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SignRepository : JpaRepository<SignEntity, Long> {

    @Query("SELECT s FROM SignEntity s JOIN FETCH s.user WHERE s.username = :username AND s.password = :password")
    fun findByUsernameAndPassword(username: String, password: String): SignEntity?

}