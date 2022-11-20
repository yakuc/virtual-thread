package jp.co.yakuc.test.virtualthread.domain.repository

import jp.co.yakuc.test.virtualthread.domain.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CommentRepository: JpaRepository<Comment, Int> {
}