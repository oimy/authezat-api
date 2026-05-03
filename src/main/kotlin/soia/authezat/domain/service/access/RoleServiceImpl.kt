package soia.authezat.domain.service.access

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import soia.authezat.domain.service.access.values.Role
import soia.authezat.infra.database.dolphin.access.RoleEntity
import soia.authezat.infra.database.dolphin.access.RoleRepository

@Service
class RoleServiceImpl(
    private val roleRepository: RoleRepository,
) :
    RoleService {

    @Transactional
    override fun save(name: String) {
        val role = RoleEntity(name = name)
        roleRepository.save(role)
    }

    @Transactional(readOnly = true)
    override fun findAll(pageable: Pageable): Page<Role> =
        roleRepository.findAll(pageable).map { Role(role = it) }

}