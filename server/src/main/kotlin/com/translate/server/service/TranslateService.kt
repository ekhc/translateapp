package com.translate.server.service

interface TranslateService {
    fun translate(text: String, tarLang: String): String
}
