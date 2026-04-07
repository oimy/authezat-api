package soia.authezat.app.utils

import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.*

private val DEFAULT_ZONE_ID = ZoneId.of(TimeZone.getDefault().id)
private val DEFAULT_ZONE_OFFSET = ZoneOffset.ofHours(TimeZone.getDefault().rawOffset / 1_000 / 60 / 60)

fun OffsetDateTime.asLocal(): LocalDateTime =
    this.atZoneSameInstant(DEFAULT_ZONE_ID).toOffsetDateTime().toLocalDateTime()

fun LocalDateTime.asOffset(): OffsetDateTime =
    OffsetDateTime.of(this, DEFAULT_ZONE_OFFSET)