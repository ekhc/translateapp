package com.translate.server.presentation.controller

import com.translate.server.presentation.dto.TranslationRequestDto
import com.translate.server.presentation.dto.TranslationResponseDto
import com.translate.server.application.service.GoogleTranslationServiceImpl
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/api")
class TranslationController(
    private val translationService: GoogleTranslationServiceImpl
) {

    @PostMapping("/translate")
    fun translate(@RequestBody request: TranslationRequestDto): TranslationResponseDto? {
        return translationService.translateText(request)
    }

    import org.slf4j.LoggerFactory

    @GetMapping("/indexPage")
    open fun index(model: Model, request: HttpServletRequest): String {
        val logger = LoggerFactory.getLogger(TranslationController::class.java)
        logger.info("index() method invoked")

        val requestUrl = request.requestURL.toString()
        val webSocketAddress = requestUrl.replace("http", "ws").
        replace("indexPage", "customWebSocketHandler")
        model.addAttribute("webSocket", webSocketAddress)
        return "index"
    }
}
