package com.mpmsg.service

import com.mpmsg.model.MessageModel
import com.mpmsg.repository.MessageRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class MessageService (
    val messageRepository: MessageRepository
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
}