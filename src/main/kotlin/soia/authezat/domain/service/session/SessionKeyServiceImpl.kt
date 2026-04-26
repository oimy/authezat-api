package soia.authezat.domain.service.session

import org.springframework.stereotype.Service

@Service(value = "simpleSessionKeyService")
class SessionKeyServiceImpl : SessionKeyService {

    companion object {
        private const val MATERIAL_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        private const val KEY_LENGTH = 64
    }

    override fun generate(): String =
        buildString(KEY_LENGTH) {
            repeat(KEY_LENGTH) {
                this.append(MATERIAL_CHARACTERS.random())
            }
        }

}