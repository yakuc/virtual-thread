package jp.co.yakuc.test.virtualthread.application.model

import jp.co.yakuc.test.virtualthread.domain.entity.Comment
import java.time.OffsetDateTime
import java.time.ZoneOffset

data class CommentResource(
    val id: Int,
    val body: String?,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime,
    val createUser: UserResource,
    val updateUser: UserResource
)

fun commentResourceOf(comment: Comment): CommentResource {
    return CommentResource(
        id = comment.id,
        body = comment.body,
        createdAt = comment.createdAt.atOffset(ZoneOffset.UTC),
        updatedAt = comment.updatedAt.atOffset(ZoneOffset.UTC),
        createUser = userResourceOf(comment.createUser),
        updateUser = userResourceOf(comment.updateUser)
    )
}