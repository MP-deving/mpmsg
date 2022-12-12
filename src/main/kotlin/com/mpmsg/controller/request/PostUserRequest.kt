package com.mpmsg.controller.request

import com.mpmsg.validation.EmailAvailable
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty


data class PostUserRequest (

    @field:NotEmpty
    var name: String,

    @field:Email
    @EmailAvailable
    var email: String,

    @field:NotEmpty
    val password: String
)