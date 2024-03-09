package com.yazid.translator.repository.online

interface contractOnlineData {
    suspend fun TranslateData(text:String):String
}