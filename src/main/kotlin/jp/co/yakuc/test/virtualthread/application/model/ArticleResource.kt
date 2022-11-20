package jp.co.yakuc.test.virtualthread.application.model

import jp.co.yakuc.test.virtualthread.domain.entity.Article
import java.time.OffsetDateTime
import java.time.ZoneOffset

data class ArticleResource(
    val id: Int,
    val subject: String,
    val body: String?,
    val comments: List<CommentResource>,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime,
    val createUser: UserResource,
    val updateUser: UserResource
) {
}

fun articleResourceOf(article: Article): ArticleResource {
    return ArticleResource(
        id = article.id,
        subject = article.subject,
        body = article.body,
        comments = article.comments.map {
            commentResourceOf(it)
        },
        createdAt = article.createdAt.atOffset(ZoneOffset.UTC),
        updatedAt = article.updatedAt.atOffset(ZoneOffset.UTC),
        createUser = userResourceOf(article.createUser),
        updateUser = userResourceOf(article.updateUser)
    )
}