package com.translate.server.application.service

import com.translate.server.presentation.dto.TranslationResponseDto

interface TranslationClient {
    fun call(
        contents: List<String>,
        targetLanguage: String,
        sourceLanguage: String,
    ): TranslationResponseDto
}
