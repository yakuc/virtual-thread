package jp.co.yakuc.test.virtualthread.application.controller

import jakarta.transaction.TransactionScoped
import jp.co.yakuc.test.virtualthread.domain.entity.Article
import jp.co.yakuc.test.virtualthread.domain.entity.Comment
import jp.co.yakuc.test.virtualthread.domain.entity.User
import jp.co.yakuc.test.virtualthread.domain.repository.ArticleRepository
import jp.co.yakuc.test.virtualthread.domain.repository.CommentRepository
import jp.co.yakuc.test.virtualthread.domain.repository.UserRepository
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("api/admin")
@Transactional
class AdminController (
    private val userRepository: UserRepository,
    private val articleRepository: ArticleRepository,
    private val commentRepository: CommentRepository
){

    /**
     * テストユーザの作成
     */
    @PostMapping("users")
    fun createTestUsers() {
        val users = mutableListOf<User>()
        for (i in 0..9) {
            val user = User().apply {
                id = 0
                name = "Test User${i + 1}"
                loginId = "user${i + 1}"
                password = "password"
                createdAt = LocalDateTime.now()
                updatedAt = LocalDateTime.now()
            }
            users.add(user)
        }
        userRepository.deleteAll()
        userRepository.saveAll(users)
    }

    /*
     * テスト記事の作成
     */
    @PostMapping("articles")
    fun createTestArticles() {
        val articles = mutableListOf<Article>()
        val comments = mutableListOf<Comment>()

        // 作成者にするユーザ情報を検索
        val createdUser = userRepository.findByLoginId("user1").orElseThrow()

        for (i in 0 .. 99) {
            val article = Article().apply {
                id = 0
                subject = "Subject-${i + 1}"
                body = "Test Body-${i + 1}"
                updateUser = createdUser
                createUser = createdUser
                createdAt = LocalDateTime.now()
                updatedAt = LocalDateTime.now()
            }
            val comment = Comment().apply {
                id = 0
                body = "Comment!-${i + 1}"
                this.article = article
                updateUser = createdUser
                createUser = createdUser
                createdAt = LocalDateTime.now()
                updatedAt = LocalDateTime.now()
            }
            article.comments = listOf(comment)

            articles.add(article)
            comments.add(comment)
        }
        articleRepository.deleteAll()
        articleRepository.saveAll(articles)
        commentRepository.deleteAll()
        commentRepository.saveAll(comments)
    }
}