package com.mpmsg.model

import com.mpmsg.enums.UserStatus
import javax.persistence.*

@Entity (name = "user")
data class UserModel (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    val id: Int? = null,

    @Column
    var name: String,

    @Column
    var email: String,

    @Column
    @Enumerated(EnumType.STRING)
    var status: UserStatus
)