package soia.authezat.infra.database.dolphin.account

import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepository : JpaRepository<ClientEntity, Long> {

    fun existsByNameAndFirstKey(name: String, firstKey: String): Boolean

    fun existsByNameAndSecondKey(name: String, secondKey: String): Boolean

}