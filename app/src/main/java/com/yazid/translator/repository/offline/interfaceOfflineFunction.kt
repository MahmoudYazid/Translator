package com.yazid.translator.repository.offline

import com.yazid.translator.model.dbTranslationModel

interface interfaceOfflineFunction {
    suspend fun contractGetData():List<dbTranslationModel>;
    fun contractInsertData(newtrans: dbTranslationModel);

}