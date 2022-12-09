package com.mpmsg.controller.request

import java.time.LocalDate


class PutMessageRequest (
    var title: String?,
    var message: String?,
    var expirationDate: LocalDate?
        )