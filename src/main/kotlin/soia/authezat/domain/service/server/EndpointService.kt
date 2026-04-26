package soia.authezat.domain.service.server

import soia.authezat.domain.service.server.values.Endpoint

interface EndpointService {

    fun findAllByServerSrl(serverSrl: Long): List<Endpoint>

}