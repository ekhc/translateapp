package com.translate.server.dto

data class TranslationResponseDto(
    val translations: List<Translation>,
    val model: String,
    val detectedLanguageCode: String
) {
    data class Translation(
        val translatedText: String,
        val model: String,
        val detectedLanguageCode: String
    )
}
