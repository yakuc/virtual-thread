package jp.co.yakuc.test.virtualthread.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "articles")
class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
    var subject: String = ""
    var body: String? = null
    @OneToMany(mappedBy = "article")
    var comments: List<Comment> = listOf()
    var createdAt: LocalDateTime = LocalDateTime.now()
    var updatedAt: LocalDateTime = LocalDateTime.now()
    @OneToOne
    @JoinColumn(name = "create_user_id")
    lateinit var createUser: User
    @OneToOne
    @JoinColumn(name = "update_user_id")
    lateinit var updateUser: User
}