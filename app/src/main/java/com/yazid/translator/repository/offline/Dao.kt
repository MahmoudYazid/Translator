package com.yazid.translator.repository.offline

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yazid.translator.model.dbTranslationModel

@Dao
interface DaoOfTransDb {
    @Query("SELECT * FROM translationTable")
    suspend fun getAll(): List<dbTranslationModel>



    @Insert
    fun insert(newtrans:dbTranslationModel)


}