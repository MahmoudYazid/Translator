package com.yazid.translator.repository.offline

import com.yazid.translator.model.dbTranslationModel
import javax.inject.Inject


class contractImplementation @Inject constructor(val DaoInst:AppDatabase):interfaceOfflineFunction {
    override suspend fun contractGetData(): List<dbTranslationModel> {

        return DaoInst.InstDaoForAccessFunc().getAll();

    }

    override fun contractInsertData( newtrans: dbTranslationModel) {
        DaoInst.InstDaoForAccessFunc().insert(newtrans);
    }
}