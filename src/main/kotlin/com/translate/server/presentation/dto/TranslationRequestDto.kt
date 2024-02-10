package com.translate.server.presentation.dto

data class TranslationRequestDto(
    val contents: List<String>,
    val sourceLanguageCode: String,
    val targetLanguageCode: String,
)
