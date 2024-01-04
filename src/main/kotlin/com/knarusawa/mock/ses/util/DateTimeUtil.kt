package com.knarusawa.mock.ses.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

object DateTimeUtil {
    fun minutesAgo(minutes: Long): LocalDateTime {
        return LocalDateTime.now().minus(minutes, ChronoUnit.MINUTES)
    }

    fun secondsAgo(seconds: Long): LocalDateTime {
        return LocalDateTime.now().minus(seconds, ChronoUnit.SECONDS)
    }

    fun timestampToLocalDateTime(timestamp: Long): LocalDateTime {
        val instant = Instant.ofEpochMilli(timestamp)
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    }
}