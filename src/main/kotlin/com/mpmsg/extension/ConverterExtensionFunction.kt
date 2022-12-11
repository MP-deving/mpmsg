package com.mpmsg.extension

import com.mpmsg.controller.request.PostMessageRequest
import com.mpmsg.controller.request.PostUserRequest
import com.mpmsg.controller.request.PutMessageRequest
import com.mpmsg.controller.request.PutUserRequest
import com.mpmsg.controller.response.MessageResponse
import com.mpmsg.controller.response.UserResponse
import com.mpmsg.enums.UserStatus
import com.mpmsg.model.MessageModel
import com.mpmsg.model.UserModel

fun PostUserRequest.toUserModel(): UserModel {
    return UserModel(name = this.name, email = this.email, status = UserStatus.ATIVO)
}

fun PutUserRequest.toUserModel (previousValue: UserModel): UserModel {
    return UserModel(id = previousValue.id, name = this.name, email = this.email, status = previousValue.status)
}

fun PostMessageRequest.toMessageModel(user: UserModel, receiversIds: List<UserModel>): MessageModel {

    return MessageModel (
        title = this.title,
        message = this.message,
        user = user,
        expirationDate = this.expirationDate,
        type = this.type,
        receiver = receiversIds
         )
}
fun PutMessageRequest.toMessageModel(previousValue: MessageModel): MessageModel {
    return MessageModel (
        id = previousValue.id,
        title = this.title?: previousValue.title,
        message = (this.message?: previousValue.message),
        user = previousValue.user,
        expirationDate = this.expirationDate,
        type = previousValue.type,
        receiver = previousValue.receiver)
}

fun UserModel.toResponse(): UserResponse {
    return UserResponse(
        id = this.id,
        name = this.name,
        email  = this.email,
        status = this.status
    )
}

    fun MessageModel.toResponse(): MessageResponse {
        return MessageResponse (
            id = this.id,
            title = this.title,
            message = this.message,
            user = this.user,
            type = this.type,
            created_at = this.createdAt,
            expirationDate = this.expirationDate,
            receiverId = this.receiver
            )
    }