package com.mpmsg.service

import com.mpmsg.enums.Roles
import com.mpmsg.enums.UserStatus
import com.mpmsg.model.UserModel
import com.mpmsg.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService (
    private val userRepository: UserRepository,
    private val messageService: MessageService,
    private val bCrypt: BCryptPasswordEncoder
) {

    fun getAll(name: String?): List<UserModel> {
        name?.let {
            return userRepository.findByNameContaining(it)
        }
        return userRepository.findAll().toList()
    }

    fun create(user: UserModel) {
        val userCopy = user.copy(
            roles = setOf(Roles.USUÁRIO),
            password = bCrypt.encode(user.password)
        )
        userRepository.save(userCopy)
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

    fun authenticate(email: String, password: String): UserModel {
        val user = userRepository.findByEmail(email)
            ?: throw Exception("Usuário não encontrado")
        if (!bCrypt.matches(password, user.password)) {
            throw Exception("Senha incorreta")
        }
        return user
    }
}