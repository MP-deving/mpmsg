package com.mpmsg.controller.response

import com.mpmsg.enums.UserStatus

data class UserResponse (
    var id: Int? = null,

    var name: String,

    var email: String,

    var status: UserStatus
)