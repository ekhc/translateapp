package com.translate.server.service

import com.google.cloud.translate.Translate
import com.google.cloud.translate.TranslateOptions
import com.google.cloud.translate.Translation

class GoogleTranslateServiceImpl(apiKey: String) : TranslateService {

    private val translate: Translate = TranslateOptions.newBuilder().setApiKey(apiKey).build().service

    override fun translate(text: String, targetLanguage: String): String {
        val translation: Translation = translate.translate(text, Translate.TranslateOption.targetLanguage(targetLanguage))
        return translation.translatedText
    }
}