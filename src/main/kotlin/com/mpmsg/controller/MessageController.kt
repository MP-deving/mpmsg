package com.mpmsg.controller

import com.mpmsg.controller.request.PostMessageRequest
import com.mpmsg.controller.request.PutMessageRequest
import com.mpmsg.controller.response.MessageResponse
import com.mpmsg.extension.toMessageModel
import com.mpmsg.extension.toResponse
import com.mpmsg.service.MessageService
import com.mpmsg.service.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("message")
class MessageController (
    val messageService: MessageService,
    val userService: UserService
){

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create (@RequestBody @Valid request: PostMessageRequest) {
        val user = userService.findById(request.userId)
        val receiversIds = messageService.findAllReceivers(request.receiverId)
        messageService.create(request.toMessageModel(user, receiversIds))
    }

    @GetMapping
    fun findAll(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<MessageResponse> {
        return messageService.findAll(pageable).map { it.toResponse() }
    }

    @GetMapping("/{title}")
    fun findByTitle(@PathVariable title: String, @PageableDefault(page = 0, size = 10) pageable: Pageable): Page<MessageResponse> {
        return messageService.findByTitle(title, pageable).map { it.toResponse() }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        messageService.delete(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody message: PutMessageRequest) {
        val messageSaved = messageService.findById(id)
        messageService.update(message.toMessageModel(messageSaved))
    }


}