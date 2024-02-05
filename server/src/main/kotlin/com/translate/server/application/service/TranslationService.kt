package com.translate.server.application.service

import com.translate.server.dto.TranslationRequestDto
import com.translate.server.dto.TranslationResponseDto

interface TranslationService {
    fun translateText(request: TranslationRequestDto): TranslationResponseDto
}
