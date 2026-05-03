package soia.authezat.domain.service.server

import soia.authezat.domain.service.server.values.Endpoint
import soia.authezat.infra.database.dolphin.server.enums.EndpointMethod
import java.time.LocalDateTime

interface EndpointService {

    fun save(serverSrl: Long, method: EndpointMethod, path: String)

    fun findAllByServerSrl(serverSrl: Long): List<Endpoint>

    fun findAllByModifiedAtGreaterThenFetchRole(afterModifiedAt: LocalDateTime): List<Endpoint>

    fun findBySrlFetchRole(srl: Long): Endpoint

}