package jp.co.yakuc.test.virtualthread.domain.repository

import jp.co.yakuc.test.virtualthread.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<User, Int> {
    /**
     * LoginIdをキーとした検索
     */
    fun findByLoginId(loginId: String): Optional<User>
}