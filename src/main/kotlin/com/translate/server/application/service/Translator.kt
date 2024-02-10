package com.translate.server.application.service

interface Translator {
    fun translate(text: String): String
}
