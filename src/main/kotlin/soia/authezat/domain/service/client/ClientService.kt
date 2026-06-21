package soia.authezat.domain.service.client

fun interface ClientService {

    fun existByNameAndKey(name: String, key: String): Boolean

}