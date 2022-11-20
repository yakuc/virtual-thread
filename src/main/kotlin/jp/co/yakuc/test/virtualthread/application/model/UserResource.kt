package jp.co.yakuc.test.virtualthread.application.model

import jp.co.yakuc.test.virtualthread.domain.entity.User
import java.time.OffsetDateTime
import java.time.ZoneOffset

data class UserResource (
    val id: Int,
    val loginId: String,
    val password: String?,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime
)

fun userResourceOf(user: User): UserResource {
    return UserResource(
        id = user.id,
        loginId = user.loginId,
        password = user.password,
        createdAt = user.createdAt.atOffset(ZoneOffset.UTC),
        updatedAt = user.createdAt.atOffset(ZoneOffset.UTC)
    )
}