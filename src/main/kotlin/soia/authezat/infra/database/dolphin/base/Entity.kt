package soia.authezat.infra.database.dolphin.base

interface Entity {

    val srl: Long

    fun create()

    fun modify()

}