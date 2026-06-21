package soia.authezat.infra.database.dolphin.account

import jakarta.persistence.Entity
import jakarta.persistence.Table
import soia.authezat.infra.database.dolphin.base.BaseEntity

@Entity
@Table(name = "clients")
class ClientEntity(
    var name: String,

    var firstKey: String,

    var secondKey: String,
) :
    BaseEntity()