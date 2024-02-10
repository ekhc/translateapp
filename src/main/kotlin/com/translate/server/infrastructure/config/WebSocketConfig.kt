package com.translate.server.infrastructure.config

import com.translate.server.infrastructure.websocket.CustomWebSocketHandler
import com.translate.server.presentation.controller.TranslationController
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
class CustomWebSocketConfig : WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(customWebSocketHandler(), "/api/ws")
        val logger = LoggerFactory.getLogger(TranslationController::class.java)
        logger.info()
    }

    @Bean
    open fun customWebSocketHandler(): CustomWebSocketHandler {
        return CustomWebSocketHandler()
    }
}