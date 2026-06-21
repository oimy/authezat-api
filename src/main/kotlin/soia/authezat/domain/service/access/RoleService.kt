package soia.authezat.domain.service.access

import soia.authezat.domain.service.access.values.Role
import java.util.UUID

interface RoleService {

    fun save(name: String, createdBy: UUID)

    fun findAll(): List<Role>

    fun findAllByUserId(userId: UUID): List<Role>

}