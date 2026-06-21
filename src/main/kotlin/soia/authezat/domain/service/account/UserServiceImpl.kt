package soia.authezat.domain.service.account

import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import soia.authezat.domain.service.access.values.Role
import soia.authezat.domain.service.account.values.User
import soia.authezat.infra.database.dolphin.account.SignEntity
import soia.authezat.infra.database.dolphin.account.SignRepository
import soia.authezat.infra.database.dolphin.account.UserEntity
import soia.authezat.infra.database.dolphin.account.UserRepository

@Service(value = "accountUserService")
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val signRepository: SignRepository,
    private val passwordEncoder: PasswordEncoder,
) :
    UserService {

    @Transactional
    override fun save(name: String, email: String, username: String, password: String) {
        val encryptPassword: String = passwordEncoder.encode(password)
            ?: throw IllegalArgumentException()

        val signEntity = SignEntity(username = username, password = encryptPassword)
        val userEntity = UserEntity(sign = signEntity, name = name, email = email)
        signEntity.user = userEntity
        userRepository.save(userEntity)
    }

    @Transactional(readOnly = true)
    override fun getByUsernameAndPassword(username: String, password: String): User {
        val signEntity: SignEntity = signRepository.findByUsername(username = username)
            ?: throw EntityNotFoundException()
        require(passwordEncoder.matches(password, signEntity.password))

        return userRepository.findBySignSrl(signEntity.srl)?.let { User(it) }
            ?: throw EntityNotFoundException()
    }

    @Transactional(readOnly = true)
    override fun findBySrl(srl: Long): User =
        userRepository.findByIdOrNull(srl)?.let { User(it) }
            ?: throw EntityNotFoundException()

    @Transactional(readOnly = true)
    override fun getRolesBySrl(srl: Long): List<Role> =
        userRepository.findByIdOrNullFetchRole(srl)?.let { user -> user.roleRelations.map { Role(role = it.role) } }
            ?: throw EntityNotFoundException()

}