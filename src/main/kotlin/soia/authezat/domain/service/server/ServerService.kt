package soia.authezat.domain.service.server

import soia.authezat.domain.service.server.values.Server
import java.util.*

interface ServerService {

    fun save(name: String, url: String, version: Short, createdBy: UUID)

    fun findAll(): List<Server>

    fun findAllByUserId(userId: UUID): List<Server>

}