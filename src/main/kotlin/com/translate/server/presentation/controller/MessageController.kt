package com.translate.server.presentation.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.translate.server.application.service.GoogleTranslationServiceImpl
import com.translate.server.presentation.dto.TranslationRequestDto
import com.translate.server.presentation.dto.TranslationResponseDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody

@Controller
class MessageController(
    private val translationService: GoogleTranslationServiceImpl,
    private val objectMapper: ObjectMapper
) {

    private val logger: Logger = LoggerFactory.getLogger(MessageController::class.java)

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    @Throws(Exception::class)
    fun send(@RequestBody request: TranslationRequestDto): TranslationResponseDto {
        logger.info("Received translation request: {}", request)
        return translationService.translateText(request)
    }
}
