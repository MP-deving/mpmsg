package com.mpmsg.events

import com.mpmsg.service.MessageService
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ExpiratedMessages (
    var messageService: MessageService,
        ) {
//    @Scheduled (fixedRate = 120000)
    @Scheduled (cron = "0 0 4 * * ?") // Apagar mensagens expiradas diariamente às 04:00
    @Async
    fun deleteMessageByDate () {
        messageService.deleteByExpirationDate()
        println("Apagando Mensagens Expiradas!")
    }

}