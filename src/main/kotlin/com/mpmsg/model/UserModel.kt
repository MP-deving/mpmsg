package com.mpmsg.model

import com.mpmsg.enums.Roles
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
    var status: UserStatus,

    @Column
    val password: String,

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Roles::class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")])
    var roles: Set<Roles> = setOf()
)