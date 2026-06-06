package soia.authezat.domain.service.server

import soia.authezat.domain.service.server.values.Server

interface ServerService {

    fun save(name: String, url: String, version: Short, createdBy: String)

    fun findAll(): List<Server>

    fun findAll(accessedBy: String): List<Server>

}