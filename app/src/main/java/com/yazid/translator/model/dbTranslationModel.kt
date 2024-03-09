package com.yazid.translator.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "translationTable")
data class dbTranslationModel(
    @PrimaryKey (autoGenerate = true) val id:Int?,
    val Original:String,
    val Translated:String,

    )
