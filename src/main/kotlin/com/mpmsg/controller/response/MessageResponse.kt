package com.mpmsg.controller.response

import com.mpmsg.model.UserModel
import org.w3c.dom.Text

class MessageResponse(
    var id: Int? = null,

    var title: String,

    var message: String,

    var user: UserModel? = null
    )