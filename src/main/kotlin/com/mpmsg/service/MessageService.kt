package com.mpmsg.service

import com.mpmsg.model.MessageModel
import com.mpmsg.model.UserModel
import com.mpmsg.repository.MessageRepository
import com.mpmsg.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class MessageService (
    val messageRepository: MessageRepository,
    val userRepository: UserRepository,
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

    @Transactional
    fun deleteByExpirationDate(date: LocalDate = LocalDate.now()) {
        messageRepository.deleteByExpirationDate(date)
    }

    fun findAllReceivers(receiversIds: Set<Int>): List<UserModel> {
        return userRepository.findAllById(receiversIds).toList()
    }

}