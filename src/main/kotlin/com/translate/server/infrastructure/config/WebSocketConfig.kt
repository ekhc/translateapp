package com.translate.server.infrastructure.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.translate.server.infrastructure.websocket.CustomWebSocketHandler
import com.translate.server.presentation.controller.TranslationController
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.converter.*
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.util.MimeTypeUtils
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.config.annotation.*

@Configuration
@EnableWebSocketMessageBroker
class CustomWebSocketConfig : WebSocketMessageBrokerConfigurer {
    private val logger = LoggerFactory.getLogger(CustomWebSocketConfig::class.java)

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(CustomWebSocketHandler(), "/chat")
    }
    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker("/topic")
        registry.setApplicationDestinationPrefixes("/app")
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/chat")
        registry.addEndpoint("/chat").withSockJS()
    }

    override fun configureMessageConverters(messageConverters: MutableList<MessageConverter>): Boolean {
        val resolver = DefaultContentTypeResolver()
        resolver.defaultMimeType = MimeTypeUtils.APPLICATION_JSON
        val converter = MappingJackson2MessageConverter()
        converter.objectMapper = ObjectMapper()
        converter.contentTypeResolver = resolver
        messageConverters.add(StringMessageConverter())
        messageConverters.add(ByteArrayMessageConverter())
        messageConverters.add(converter)
        return false
    }
}
