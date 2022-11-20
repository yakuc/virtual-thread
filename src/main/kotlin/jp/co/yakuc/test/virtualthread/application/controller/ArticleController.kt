package jp.co.yakuc.test.virtualthread.application.controller

import jp.co.yakuc.test.virtualthread.application.model.ArticleResource
import jp.co.yakuc.test.virtualthread.application.model.articleResourceOf
import jp.co.yakuc.test.virtualthread.domain.repository.ArticleRepository
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class ArticleController(
    private val articleRepository: ArticleRepository
) {
    @GetMapping("articles")
    @Transactional(readOnly = true)
    fun articles(): List<ArticleResource> {
        val articles = articleRepository.findAllFetch()
        return articles.map {
            articleResourceOf(it)
        }
    }
}