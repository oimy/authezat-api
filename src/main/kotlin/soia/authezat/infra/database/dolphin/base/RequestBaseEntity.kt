package soia.authezat.infra.database.dolphin.base

import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.MappedSuperclass
import soia.authezat.infra.database.dolphin.base.enums.RequestStatus

@MappedSuperclass
abstract class RequestBaseEntity(
    @Enumerated(value = EnumType.STRING)
    var status: RequestStatus = RequestStatus.PENDING,

    var reason: String? = null,
) :
    BaseEntity() {

    fun accept() {
        require(this.status == RequestStatus.PENDING)
        this.status = RequestStatus.APPROVE
    }

    fun reject(reason: String) {
        require(this.status == RequestStatus.PENDING)
        this.status = RequestStatus.REJECT
        this.reason = reason
    }

}