package soia.authezat.domain.service.server

import soia.authezat.domain.service.server.values.Server

interface ServerService {

    fun findAll(): List<Server>

}