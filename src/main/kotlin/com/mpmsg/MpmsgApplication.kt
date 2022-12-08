package com.mpmsg

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MpmsgApplication

fun main(args: Array<String>) {
	runApplication<MpmsgApplication>(*args)
}
