package com.translate.server.application.service

import com.translate.server.presentation.dto.TranslationRequestDto
import com.translate.server.presentation.dto.TranslationResponseDto

interface TranslationService {
    fun translateText(request: TranslationRequestDto): TranslationResponseDto
}
