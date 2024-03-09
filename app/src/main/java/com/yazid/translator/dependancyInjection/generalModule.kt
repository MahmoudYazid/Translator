package com.yazid.translator.dependancyInjection

import android.content.Context
import androidx.room.Room
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import com.yazid.translator.repository.offline.AppDatabase
import com.yazid.translator.repository.offline.contractImplementation
import com.yazid.translator.repository.offline.interfaceOfflineFunction
import com.yazid.translator.repository.online.contractOnlineData
import com.yazid.translator.repository.online.onlineDataImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class generalModule {

    @Provides
    @Singleton
    fun GenerateDb(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "translationdb"
        ).build()
    }
    @Provides
    @Singleton
    fun getDbImplementationInst(implementation:contractImplementation): interfaceOfflineFunction {
        return implementation
    }
    @Provides
    @Singleton
    fun getOnlineImplementationInst(implementation:onlineDataImplementation): contractOnlineData {
        return implementation
    }
    @Provides
    @Singleton
    fun TranslatorBuilder(): TranslatorOptions {
        return  TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.GERMAN)
            .build()
    }

    @Provides
    @Singleton
    fun TranslatorInstBuilder(TranslatorOptionsInst:TranslatorOptions): Translator {
        return  Translation.getClient(TranslatorOptionsInst);
    }

    @Provides
    @Singleton
    fun TranslatorDownloadCondition(): DownloadConditions {
        return  DownloadConditions.Builder()
            .requireWifi()
            .build()
    }
}