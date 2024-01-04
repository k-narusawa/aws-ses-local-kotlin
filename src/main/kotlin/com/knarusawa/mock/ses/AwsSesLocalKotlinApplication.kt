package com.knarusawa.mock.ses

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SesMockApplication

fun main(args: Array<String>) {
    runApplication<com.knarusawa.mock.ses.SesMockApplication>(*args)
}
