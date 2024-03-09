package com.yazid.translator.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.mlkit.nl.translate.Translator
import com.yazid.translator.model.dbTranslationModel
import com.yazid.translator.repository.offline.interfaceOfflineFunction
import com.yazid.translator.repository.online.contractOnlineData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class viewModel @Inject constructor (
    val offlineRepo:interfaceOfflineFunction,
    val onlineRepo:contractOnlineData

):ViewModel() {
    val oldTransLiveData=MutableLiveData<List<dbTranslationModel>>();
    val LastTranslatedWordLiveData=MutableLiveData<String>();
    fun viewModel_getData(){
            viewModelScope.launch {
                launch (Dispatchers.IO){
                   val newValue= offlineRepo.contractGetData()
                    withContext(Dispatchers.Main){
                        SetLiveData(newValue)
                    }

                }
            }
    }
    fun SetLiveData(newList:List<dbTranslationModel>){
        oldTransLiveData.value= newList

    }
    fun viewModel_Insert( newtrans: dbTranslationModel){
        viewModelScope.launch {
            launch (Dispatchers.IO){
                offlineRepo.contractInsertData(newtrans)
                viewModel_getData()

            }
        }
    }

    fun viewModel_Translate( newtrans: String){
        viewModelScope.launch {
            launch (Dispatchers.IO){
                val new_data = onlineRepo.TranslateData(newtrans)
                Log.e("newdata",new_data)

                val newInstOFDataClass = dbTranslationModel(
                    id = null,
                    Original = newtrans.toString(),
                    Translated = new_data

                )
                viewModel_Insert(newInstOFDataClass)

                withContext(Dispatchers.Main){
                    LastTranslatedWordLiveData.value = new_data.toString()
                }
            }
        }
    }


}