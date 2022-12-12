package com.mpmsg.service

import com.mpmsg.repository.UserRepository
import com.mpmsg.security.UserCustomDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsCustomService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(id: String): UserDetails {
       val user =  userRepository.findById(id.toInt())
            .orElseThrow()
        return UserCustomDetails(user)
    }
}