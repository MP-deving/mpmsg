package com.mpmsg.repository

import com.mpmsg.model.MessageModel
import com.mpmsg.model.UserModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface MessageRepository: JpaRepository<MessageModel, Int> {

    fun findByUser (user: UserModel): List<MessageModel>
//    fun findAll(pageable: Pageable): Page<MessageModel>
    fun findByTitleContaining (title: String, pageable: Pageable): Page<MessageModel>
}