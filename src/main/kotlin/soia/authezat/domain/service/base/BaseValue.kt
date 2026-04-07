package soia.authezat.domain.service.base

import java.time.LocalDateTime

interface BaseValue {
    val srl: Long
    val createdBy: String
    val createdAt: LocalDateTime
    val modifiedBy: String
    val modifiedAt: LocalDateTime
}