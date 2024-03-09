package com.yazid.translator.repository.online

import android.util.Log
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.Translator
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class onlineDataImplementation @Inject constructor(
    val TranslatorModel: Translator,
    val conditions: DownloadConditions
):contractOnlineData {
    override suspend fun TranslateData(text: String): String {
        return suspendCancellableCoroutine { continuation ->
            TranslatorModel.downloadModelIfNeeded(conditions)
                .addOnSuccessListener {
                    TranslatorModel.translate(text)
                        .addOnSuccessListener { translatedText ->
                            continuation.resume(translatedText)
                        }
                        .addOnFailureListener { exception ->
                            continuation.resumeWithException(exception)
                        }
                }
                .addOnFailureListener { exception ->
                    continuation.resumeWithException(exception)
                }
        }
    }

}