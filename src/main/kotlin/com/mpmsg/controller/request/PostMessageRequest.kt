package com.mpmsg.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import com.mpmsg.enums.MessageTypes
import java.time.LocalDate
import javax.validation.constraints.NotEmpty

class PostMessageRequest (

    @field:NotEmpty(message = "Titulo deve ser Informado")
    var title: String,

    @field:NotEmpty(message = "A mensagem deve ser informada")
    var message: String,

    @JsonAlias("user_id")
    var userId: Int,

    @JsonAlias("receiver_id")
    val receiverId: Set<Int>,

    val type: MessageTypes,

    var expirationDate: LocalDate?
    )