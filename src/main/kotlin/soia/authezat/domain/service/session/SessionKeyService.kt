package soia.authezat.domain.service.session

fun interface SessionKeyService {

    fun generate(): String

}