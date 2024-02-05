package com.translate.server.application.service

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import com.translate.server.dto.TranslationRequestDto
import com.translate.server.dto.TranslationResponseDto
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Value
import com.google.gson.Gson
import org.springframework.stereotype.Component

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