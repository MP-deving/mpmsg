package com.mpmsg.controller

import com.mpmsg.controller.request.PostUserRequest
import com.mpmsg.controller.request.PutUserRequest
import com.mpmsg.controller.response.UserResponse
import com.mpmsg.extension.toResponse
import com.mpmsg.extension.toUserModel
import com.mpmsg.service.MessageService
import com.mpmsg.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("user")
class UserController (
    val userService: UserService,
) {

    @GetMapping
    fun getAll(@RequestParam name: String?): List<UserResponse> {
        return userService.getAll(name).map{ it.toResponse() }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid user: PostUserRequest) {
        userService.create(user.toUserModel())
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Int): UserResponse {
        return userService.findById(id).toResponse()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update (@PathVariable id: Int, @RequestBody @Valid user: PutUserRequest) {
        val userSaved = userService.findById(id)
        userService.update(user.toUserModel(userSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
     userService.delete(id)
    }
}