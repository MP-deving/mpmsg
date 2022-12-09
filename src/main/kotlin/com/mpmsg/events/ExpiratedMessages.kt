package com.mpmsg.events

import com.mpmsg.service.MessageService
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ExpiratedMessages (
    var messageService: MessageService,
        ) {
    @Scheduled (fixedRate = 120000)
//    @Scheduled (cron = "* * * 0/1 * *") // Apagar mensagens expiradas diariamente
    @Async
    fun deleteMessageByDate () {
        messageService.deleteByExpirationDate()
        println("Apagando Mensagens Expiradas!")
    }

}