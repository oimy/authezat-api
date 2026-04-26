package soia.authezat.domain.service.access

import soia.authezat.domain.service.access.values.RoleEndpoint
import java.time.LocalDateTime

interface RoleEndpointService {

    fun save(roleSrl: Long, endpointSrl: Long)

    fun findAll(): List<RoleEndpoint>

    fun findByModifiedAtGreaterThan(modifiedAt: LocalDateTime): List<RoleEndpoint>

}