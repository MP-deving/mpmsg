package com.mpmsg.controller.response

import com.mpmsg.model.UserModel
import org.w3c.dom.Text
import java.time.LocalDate
import java.time.LocalDateTime

class MessageResponse(
    var id: Int? = null,

    var title: String,

    var message: String,

    var user: UserModel? = null,

    val created_at: LocalDateTime?,

    var expirationDate: LocalDate?
)