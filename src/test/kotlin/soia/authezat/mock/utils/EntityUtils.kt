package soia.authezat.mock.utils

import soia.authezat.infra.database.dolphin.base.BaseEntity
import soia.authezat.infra.database.dolphin.base.Entity
import soia.authezat.infra.database.dolphin.base.ImmutableBaseEntity
import java.lang.reflect.Field
import java.time.LocalDateTime
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaField

fun <T : ImmutableBaseEntity> T.commit(
    createdBy: String = "created-by",
    createdAt: LocalDateTime = LocalDateTime.now()
): T {
    this.setSrl(srl)

    this.createdBy = createdBy
    this.createdAt = createdAt

    return this
}

fun <T : BaseEntity> T.commit(
    srl: Long,
    createdBy: String = "created-by",
    createdAt: LocalDateTime = LocalDateTime.now(),
    modifiedBy: String = "modified-by",
    modifiedAt: LocalDateTime = LocalDateTime.now()
): T {
    this.setSrl(srl)

    this.createdBy = createdBy
    this.createdAt = createdAt
    this.modifiedBy = modifiedBy
    this.modifiedAt = modifiedAt

    return this
}

private fun <T : Entity> T.setSrl(srl: Long) {
    val property: KProperty1<out T, *> = this::class.memberProperties.first { it.name == "srl" }
    val field: Field = property.javaField ?: throw RuntimeException()
    field.isAccessible = true
    field.set(this, srl)
}