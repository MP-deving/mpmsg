package com.mpmsg

import com.mpmsg.service.MessageService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SpringBootApplication
@EnableScheduling
@EnableAsync
class MpmsgApplication

fun main(args: Array<String>) {
	runApplication<MpmsgApplication>(*args)
}
