package com.victoryzzi.android.apps.opgg.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.victoryzzi.android.apps.opgg.extension.asCallbackFlow
import com.victoryzzi.android.apps.opgg.model.games.Games
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
object GameRepo {
    private var preGames: Games? = null
    val requestGameLiveData = MutableLiveData<String>()
    val requestGameLiveDataMore = MutableLiveData<String>()

    val responseGameLiveData: LiveData<Games> = requestGameLiveData.switchMap { query ->
        liveData(Dispatchers.IO) {
            NetworkHelper.gameService.loadGames(query).asCallbackFlow()
                .catch { e ->
                    e.printStackTrace()
                }.collect {
                    preGames = it
                    emit(it)
                }
        }
    }

    val responseGameLiveDataMore: LiveData<Games?> = requestGameLiveDataMore.switchMap { query ->
        liveData(Dispatchers.IO) {
            NetworkHelper.gameService.loadGames(query).asCallbackFlow()
                .catch { e ->
                    e.printStackTrace()
                }.collect {
                    preGames?.addAllGames(it)
                    emit(preGames)
                }
        }
    }
}