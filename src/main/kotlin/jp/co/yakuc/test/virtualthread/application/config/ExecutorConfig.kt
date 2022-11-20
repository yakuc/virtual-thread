package jp.co.yakuc.test.virtualthread.application.config

import org.apache.coyote.ProtocolHandler
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.AsyncTaskExecutor
import org.springframework.core.task.support.TaskExecutorAdapter
import java.util.concurrent.Executors


@Configuration
/**
 * Virtual Threadを生成する設定。
 * @see https://spring.io/blog/2022/10/11/embracing-virtual-threads
 */
class ExecutorConfig {

    @Bean(TaskExecutionAutoConfiguration.APPLICATION_TASK_EXECUTOR_BEAN_NAME)
    fun asyncTaskExecutor(): AsyncTaskExecutor? {
        return TaskExecutorAdapter(Executors.newVirtualThreadPerTaskExecutor())
    }

    @Bean
    fun protocolHandlerVirtualThreadExecutorCustomizer(): TomcatProtocolHandlerCustomizer<*>? {
        return TomcatProtocolHandlerCustomizer { protocolHandler: ProtocolHandler ->
            protocolHandler.executor = Executors.newVirtualThreadPerTaskExecutor()
        }
    }
}