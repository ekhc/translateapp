package com.translate.server.infrastructure.websocket

import org.slf4j.LoggerFactory
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.io.IOException

class CustomWebSocketHandler : TextWebSocketHandler() {
    private val logger = LoggerFactory.getLogger(CustomWebSocketHandler::class.java)
    @Throws(IOException::class)
    public override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        logger.info("Received WebSocket message: ${message.payload}")
        session.sendMessage(TextMessage(message.payload))
        logger.info("Sent WebSocket message: ${message.payload}")
    }
}
