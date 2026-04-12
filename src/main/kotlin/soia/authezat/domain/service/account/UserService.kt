package soia.authezat.domain.service.account

import soia.authezat.domain.service.account.values.User

interface UserService {

    fun save(name: String, email: String, username: String, password: String)

    fun getByUsernameAndPassword(username: String, password: String): User

    fun findBySrl(srl: Long): User

}