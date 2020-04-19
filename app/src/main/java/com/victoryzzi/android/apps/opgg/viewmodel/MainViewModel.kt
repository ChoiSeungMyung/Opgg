package com.victoryzzi.android.apps.opgg.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.victoryzzi.android.apps.opgg.adapter.list.GameListAdapter
import com.victoryzzi.android.apps.opgg.adapter.list.LeagueListAdapter
import com.victoryzzi.android.apps.opgg.extension.asCallbackFlow
import com.victoryzzi.android.apps.opgg.model.Games
import com.victoryzzi.android.apps.opgg.model.Summoners
import com.victoryzzi.android.apps.opgg.service.ServiceRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val requestGameLiveData = MutableLiveData<String>()
    val responseGameLiveData: LiveData<Games>

    private val requestSummonerLiveData = MutableLiveData<String>()
    val responseSummonerLiveData: LiveData<Summoners>

    val gameListAdapter = GameListAdapter()
    val leagueListAdapter = LeagueListAdapter()

    init {
        responseGameLiveData = requestGameLiveData.switchMap { query ->
            liveData(Dispatchers.IO) {
                ServiceRepo.gameService.loadGames(query).asCallbackFlow().catch { e ->
                    e.printStackTrace()
                }.collect {
                    emit(it)
                }
            }
        }

        responseSummonerLiveData = requestSummonerLiveData.switchMap { summonerName ->
            liveData(Dispatchers.IO) {
                ServiceRepo.summonerService.loadSummoner(summonerName).asCallbackFlow().catch { e ->
                    e.printStackTrace()
                }.collect {
                    emit(it)
                }
            }
        }
    }

    fun requestSummoner(summonerName: String) {
        requestSummonerLiveData.postValue(summonerName)
    }

    fun requestGame(createDate: String) {
        requestGameLiveData.postValue(createDate)
    }
}