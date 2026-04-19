package soia.authezat.infra.database.dolphin.access

import jakarta.persistence.Entity
import jakarta.persistence.Table
import soia.authezat.infra.database.dolphin.base.BaseEntity


@Entity
@Table(name = "roles")
class RoleEntity(
    var name: String,
) :
    BaseEntity()