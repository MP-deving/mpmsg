package com.mpmsg.repository

import com.mpmsg.model.UserModel
import org.springframework.data.repository.CrudRepository


interface UserRepository: CrudRepository<UserModel, Int> {

    fun findByNameContaining(name: String): List<UserModel>
    fun existsByEmail(email:String): Boolean
}