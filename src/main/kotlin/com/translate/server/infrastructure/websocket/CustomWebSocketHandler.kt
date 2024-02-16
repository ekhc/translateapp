package com.translate.server.infrastructure.websocket

import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.io.IOException

class CustomWebSocketHandler : TextWebSocketHandler() {
    @Throws(IOException::class)
    public override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        session.sendMessage(TextMessage(message.payload))
    }
}