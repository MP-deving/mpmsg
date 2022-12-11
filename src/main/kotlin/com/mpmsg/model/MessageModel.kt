package com.mpmsg.model

import com.mpmsg.enums.MessageTypes
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne

@Entity(name = "message")
data class MessageModel (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var title: String,

    @Column
    val message: String,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: UserModel? = null,

    @ManyToMany
    @JoinTable(name = "receivers_id",
        joinColumns = [JoinColumn(name = "message_id")],
        inverseJoinColumns = [JoinColumn(name = "receiver_id")])
    val receiver: List<UserModel>,

    @Column
    @Enumerated(EnumType.STRING)
    val type: MessageTypes,

    @Column(name = "created_at")
    val createdAt: LocalDateTime? = LocalDateTime.now(),

    @Column
    var expirationDate: LocalDate?
)