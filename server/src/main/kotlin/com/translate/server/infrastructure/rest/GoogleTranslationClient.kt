package com.translate.server.infrastructure.rest

import com.google.gson.Gson
import com.translate.server.dto.TranslationResponseDto
import com.translate.server.application.service.GoogleTranslationServiceImpl
import com.translate.server.application.service.TranslationClient
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class GoogleTranslationClient(
    @Value("\${google.translate.api.url}")
    private val apiUrl: String,
    private val customHttpClient: OkHttpClient
): TranslationClient {

    companion object {
        private val JSON_MEDIA_TYPE = "application/json; charset=utf-8".toMediaType()
    }

    override fun call(
        contents: List<String>,
        targetLanguage: String,
        sourceLanguage: String,
    ): TranslationResponseDto {
        val projectId = System.getenv("PROJECT_ID")
        val authToken = System.getenv("GOOGLE_ACCESS_TOKEN")

        val parent = "projects/$projectId"
        val requestBody = Gson().toJson(
            mapOf(
                "sourceLanguageCode" to sourceLanguage,
                "targetLanguageCode" to targetLanguage,
                "contents" to contents
            )
        ).toRequestBody(JSON_MEDIA_TYPE)

        val httpRequest = Request.Builder()
            .url("$apiUrl/$parent:translateText")
            .post(requestBody)
            .addHeader("Authorization", "Bearer $authToken")
            .addHeader("Content-Type", JSON_MEDIA_TYPE.type)
            .addHeader("x-goog-user-project", "$projectId")
            .build()

        customHttpClient.newCall(httpRequest).execute().use { response ->
            if (!response.isSuccessful) {
                val errorBody = response.body?.string()
                throw Exception("Error translating text: $errorBody")
            }

            val responseBody = response.body?.string()
                ?: throw Exception("Error translating text. Response body is null.")

            return Gson().fromJson(responseBody, TranslationResponseDto::class.java)
        }
    }
}
