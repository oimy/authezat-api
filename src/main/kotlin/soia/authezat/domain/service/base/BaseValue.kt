package soia.authezat.domain.service.base

import java.time.LocalDateTime
import java.util.*

interface BaseValue {
    val srl: Long
    val createdBy: UUID
    val createdAt: LocalDateTime
    val modifiedBy: UUID
    val modifiedAt: LocalDateTime
}