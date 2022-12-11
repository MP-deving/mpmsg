package com.mpmsg.controller.response

import com.mpmsg.enums.MessageTypes
import com.mpmsg.model.UserModel
import java.time.LocalDate
import java.time.LocalDateTime

class MessageResponse(
    var id: Int? = null,

    var title: String,

    var message: String,

    var user: UserModel? = null,

    val receiverId: List<UserModel>,

    val type: MessageTypes,

    val created_at: LocalDateTime?,

    var expirationDate: LocalDate?
)