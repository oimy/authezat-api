package soia.authezat.domain.service.account

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import soia.authezat.domain.service.account.values.User
import soia.authezat.infra.database.dolphin.account.SignEntity
import soia.authezat.infra.database.dolphin.account.SignRepository
import soia.authezat.infra.database.dolphin.account.UserEntity
import soia.authezat.infra.database.dolphin.account.UserRepository

@Service(value = "AccountUserService")
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val signRepository: SignRepository,
) :
    UserService {

    @Transactional
    override fun save(name: String, email: String, username: String, password: String) {
        val signEntity = SignEntity(username = username, password = password)
        val userEntity = UserEntity(sign = signEntity, name = name, email = email)
        signEntity.user = userEntity
        userRepository.save(userEntity)
    }

    @Transactional(readOnly = true)
    override fun getByUsernameAndPassword(username: String, password: String): User {
        val signEntity: SignEntity = signRepository.findByUsernameAndPassword(username = username, password = password)
            ?: throw EntityNotFoundException()

        return User(signEntity.user)
    }

}