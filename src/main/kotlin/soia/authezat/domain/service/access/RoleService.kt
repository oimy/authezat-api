package soia.authezat.domain.service.access

import soia.authezat.domain.service.access.values.Role

interface RoleService {

    fun save(name: String, createdBy: String)

    fun findAll(): List<Role>

    fun findAll(accessedBy: String): List<Role>

}