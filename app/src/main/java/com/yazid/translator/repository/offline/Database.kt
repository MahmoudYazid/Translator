package com.yazid.translator.repository.offline

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yazid.translator.model.dbTranslationModel

@Database(entities = [dbTranslationModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun InstDaoForAccessFunc(): DaoOfTransDb
}