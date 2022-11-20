package jp.co.yakuc.test.virtualthread.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "comments")
class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
    var body: String? = null
    @ManyToOne
    lateinit var article: Article
    var createdAt: LocalDateTime = LocalDateTime.now()
    var updatedAt: LocalDateTime = LocalDateTime.now()
    @OneToOne
    @JoinColumn(name = "create_user_id")
    lateinit var createUser: User
    @OneToOne
    @JoinColumn(name = "update_user_id")
    lateinit var updateUser: User
}