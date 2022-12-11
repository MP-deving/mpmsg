package com.mpmsg.service

import com.mpmsg.enums.UserStatus
import com.mpmsg.model.UserModel
import com.mpmsg.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService (
    val userRepository: UserRepository,
    val messageService: MessageService
) {

    fun getAll(name: String?): List<UserModel> {
        name?.let {
            return userRepository.findByNameContaining(it)
        }
        return userRepository.findAll().toList()
    }

    fun create(user: UserModel) {
        userRepository.save(user)
    }

    fun findById(id: Int): UserModel {
        return userRepository.findById(id).orElseThrow()
    }

    fun update(user: UserModel) {
        if(!userRepository.existsById(user.id!!)) throw Exception()
        userRepository.save(user)
    }

    fun delete(id: Int) {
        val user = findById(id)

        user.status = UserStatus.INATIVO
        userRepository.save(user)
    }

    fun emailAvailable(email: String): Boolean {
        return !userRepository.existsByEmail(email)
    }

    fun findAllByIds(receiversIds: Set<Int>): List<UserModel> {
        return userRepository.findAllById(receiversIds).toList()
    }
}