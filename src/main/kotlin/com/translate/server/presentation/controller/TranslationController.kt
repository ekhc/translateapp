package com.translate.server.presentation.controller

import com.translate.server.presentation.dto.TranslationRequestDto
import com.translate.server.presentation.dto.TranslationResponseDto
import com.translate.server.application.service.GoogleTranslationServiceImpl
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class TranslationController(
    private val translationService: GoogleTranslationServiceImpl
) {

    @PostMapping("/translate")
    fun translate(@RequestBody request: TranslationRequestDto): TranslationResponseDto? {
        return translationService.translateText(request)
    }
}
