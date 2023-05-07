package com.knarusawa.mock.ses.sesmock.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

object DateTimeUtil {
  fun minutesAgo(minutes: Long): LocalDateTime {
    return LocalDateTime.now().minus(minutes, ChronoUnit.MINUTES)
  }

  fun timestampToLocalDateTime(timestamp: Long): LocalDateTime {
    val instant = Instant.ofEpochMilli(timestamp)
    return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
  }
}