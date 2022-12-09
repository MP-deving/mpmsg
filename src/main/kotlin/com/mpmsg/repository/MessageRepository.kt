package com.mpmsg.repository

import com.mpmsg.model.MessageModel
import com.mpmsg.model.UserModel
import org.springframework.context.annotation.Bean
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface MessageRepository: JpaRepository<MessageModel, Int> {
    fun findByUser (user: UserModel): List<MessageModel>
//    fun findAll(pageable: Pageable): Page<MessageModel>
    fun findByTitleContaining (title: String, pageable: Pageable): Page<MessageModel>

//    @Query
//        ("SELECT * FROM message WHERE expiration_date = :date")
//    fun findByExpirationDate (@Param("date") date: LocalDate): List<MessageModel>
    @Query
    @Modifying
    @Bean
    ("DELETE FROM message WHERE expiration_date = :date")
    fun deleteByExpirationDate(date: LocalDate) {
    }
}