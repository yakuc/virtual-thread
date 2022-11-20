package jp.co.yakuc.test.virtualthread.application.controller

import jp.co.yakuc.test.virtualthread.application.model.UserResource
import jp.co.yakuc.test.virtualthread.application.model.userResourceOf
import jp.co.yakuc.test.virtualthread.domain.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class UserController (
    private val userRepository: UserRepository){
    private val log = LoggerFactory.getLogger(UserController::class.java)

    @GetMapping("users")
    @Transactional(readOnly = true)
    fun users(): List<UserResource> {
        log.debug("${Thread.currentThread().toString()} /api/users")

        val users = userRepository.findAll()
        return users.map {
            userResourceOf(it)
        }
    }
}