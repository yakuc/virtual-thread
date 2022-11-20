package jp.co.yakuc.test.virtualthread.domain.repository

import jp.co.yakuc.test.virtualthread.domain.entity.Article
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ArticleRepository: JpaRepository<Article, Int> {


    @Query("SELECT a FROM Article a " +
            "LEFT JOIN FETCH a.comments " +
            "LEFT JOIN FETCH a.createUser " +
            "LEFT JOIN FETCH a.updateUser")
    fun findAllFetch(): List<Article>
}