package com.translate.server.application.service

import com.translate.server.presentation.dto.TranslationRequestDto
import com.translate.server.presentation.dto.TranslationResponseDto
import org.springframework.stereotype.Service

@Service
class GoogleTranslationServiceImpl(
    private val googleTranslationClient: TranslationClient
) : TranslationService {

    override fun translateText(request: TranslationRequestDto): TranslationResponseDto {
        val (text, targetLanguage, sourceLanguage) = request
        return googleTranslationClient.call(
            text, targetLanguage, sourceLanguage
        )
    }
}
