package com.mpmsg.model

import org.w3c.dom.Text
import javax.persistence.Column
import javax.persistence.Entity
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
    @ManyToMany
    @JoinColumn(name = "user_id")
    @JoinTable(name = "user_message")
    var user: UserModel? = null

)