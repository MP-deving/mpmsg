package com.mpmsg.controller.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

class PutUserRequest (
    @field:NotEmpty
    var name: String,

    @field:Email
    var email: String
        )