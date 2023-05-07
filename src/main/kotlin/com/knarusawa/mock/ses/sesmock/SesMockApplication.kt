package com.knarusawa.mock.ses.sesmock

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SesMockApplication

fun main(args: Array<String>) {
	runApplication<SesMockApplication>(*args)
}
