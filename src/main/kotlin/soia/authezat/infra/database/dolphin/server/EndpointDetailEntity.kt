package soia.authezat.infra.database.dolphin.server

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import soia.authezat.infra.database.dolphin.base.BaseEntity

@Entity
@Table(name = "endpoint_details")
class EndpointDetailEntity(
    @JoinColumn(nullable = false, updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    var endpoint: EndpointEntity,

    var operationId: String,

    var summary: String?,

    var description: String?,

    @JdbcTypeCode(value = SqlTypes.JSON)
    var tags: List<String>,

    @JdbcTypeCode(value = SqlTypes.JSON)
    var variables: List<Map<String, Any>>,

    @JdbcTypeCode(value = SqlTypes.JSON)
    var parameters: List<Map<String, Any>>,

    @JdbcTypeCode(value = SqlTypes.JSON)
    var requestBodies: List<Map<String, Any>>,

    @JdbcTypeCode(value = SqlTypes.JSON)
    var responseBodies: List<Map<String, Any>>,
) :
    BaseEntity()