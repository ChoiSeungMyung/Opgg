package com.victoryzzi.android.apps.opgg.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.victoryzzi.android.apps.opgg.extension.asCallbackFlow
import com.victoryzzi.android.apps.opgg.model.Games
import com.victoryzzi.android.apps.opgg.service.GameRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val requestGameLiveData = MutableLiveData<String>()
    val responseGameLiveData: LiveData<Games>

    init {
        responseGameLiveData = requestGameLiveData.switchMap { query ->
            liveData(Dispatchers.IO) {
                GameRepo.gameService.loadGames(query).asCallbackFlow().catch { e ->
                    e.printStackTrace()
                }.collect {
                    emit(it)
                }
            }
        }
    }

    fun requestGame(createDate: String) {
        requestGameLiveData.postValue(createDate)
    }
}