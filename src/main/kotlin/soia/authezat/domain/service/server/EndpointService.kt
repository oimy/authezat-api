package soia.authezat.domain.service.server

import soia.authezat.app.controller.server.payloads.EndpointSavePayload
import soia.authezat.domain.service.server.values.Endpoint
import soia.authezat.infra.database.dolphin.server.enums.EndpointMethod
import java.time.LocalDateTime

interface EndpointService {

    fun saveAll(serverSrl: Long, endpointSaves: List<EndpointSavePayload>, createdBy: String)

    fun findAllByServerSrl(serverSrl: Long, accessedBy: String): List<Endpoint>

    fun findAllByModifiedAtGreaterThenFetchRole(afterModifiedAt: LocalDateTime): List<Endpoint>

    fun findBySrlFetchRole(srl: Long): Endpoint

    fun addRole(srl: Long, roleSrl: Long, addedBy: String)

    fun removeRole(srl: Long, roleSrl: Long, removedBy: String)

}