package com.mpmsg.service

import com.mpmsg.model.MessageModel
import com.mpmsg.repository.MessageRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class MessageService (
    val messageRepository: MessageRepository,
    val currrentDate: String = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
) {

    fun create(message: MessageModel) {
        messageRepository.save(message)
    }

    fun findAll (pageable: Pageable): Page<MessageModel> {
        return messageRepository.findAll(pageable)
    }

    fun findById(id: Int): MessageModel {
        return messageRepository.findById(id).orElseThrow()
    }

    fun delete(id: Int) {
        val message = findById(id)
        messageRepository.delete(message)
    }

    fun update(message: MessageModel) {
        messageRepository.save(message)
    }

    fun findByTitle(title: String, pageable: Pageable): Page<MessageModel> {
        return messageRepository.findByTitleContaining(title, pageable)
    }

//    fun findByExpirationDate (expiratedDate: LocalDate): List<MessageModel> {
//        return messageRepository.findByExpirationDate(expiratedDate)
//    }
//
//    fun findAllByDate (date: LocalDate): List<MessageModel> {
//        return messageRepository.findByExpirationDate(date)
//    }

    @Transactional
    fun deleteByExpirationDate(date: LocalDate = LocalDate.now()) {
        messageRepository.deleteByExpirationDate(date)
    }

}