package soia.authezat.infra.database.dolphin.server

import org.springframework.data.jpa.repository.JpaRepository

interface ServerRepository : JpaRepository<ServerEntity, Long>