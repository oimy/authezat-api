package soia.authezat.infra.database.dolphin.account

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import soia.authezat.infra.database.dolphin.base.BaseEntity

@Entity
@Table(name = "signs")
class SignEntity(
    var username: String,

    var password: String,
) :
    BaseEntity() {

    @OneToOne(mappedBy = "sign", cascade = [CascadeType.ALL])
    lateinit var user: UserEntity

}