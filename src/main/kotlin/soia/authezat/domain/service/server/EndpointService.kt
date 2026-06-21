package soia.authezat.domain.service.server

import soia.authezat.app.controller.server.payloads.EndpointSavePayload
import soia.authezat.domain.service.server.values.Endpoint
import soia.authezat.domain.service.server.values.EndpointDetail
import java.time.LocalDateTime
import java.util.*

interface EndpointService {

    fun saveAll(serverSrl: Long, endpointSaves: List<EndpointSavePayload>, createdBy: UUID)

    fun findAllByServerSrl(serverSrl: Long): List<Endpoint>

    fun findAllByServerSrlAndUserId(serverSrl: Long, userId: UUID): List<Endpoint>

    fun findAllByModifiedAtGreaterThenFetchRole(afterModifiedAt: LocalDateTime): List<Endpoint>

    fun findBySrlFetchRole(srl: Long): Endpoint

    fun getDetail(srl: Long): EndpointDetail

    fun addRole(srl: Long, roleSrl: Long, addedBy: UUID)

    fun removeRole(srl: Long, roleSrl: Long, removedBy: UUID)

}