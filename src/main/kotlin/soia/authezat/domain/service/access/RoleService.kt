package soia.authezat.domain.service.access

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import soia.authezat.domain.service.access.values.Role

interface RoleService {

    fun save(name: String)

    fun findAll(pageable: Pageable): Page<Role>

}