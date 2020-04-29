package com.victoryzzi.android.apps.opgg.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.victoryzzi.android.apps.opgg.adapter.list.GameListAdapter
import com.victoryzzi.android.apps.opgg.adapter.list.LeagueListAdapter
import com.victoryzzi.android.apps.opgg.data.model.Summoners
import com.victoryzzi.android.apps.opgg.data.model.games.Games
import com.victoryzzi.android.apps.opgg.data.repository.GameRepo.preGames
import com.victoryzzi.android.apps.opgg.data.repository.GameRepo.requestGameLiveData
import com.victoryzzi.android.apps.opgg.data.repository.GameRepo.requestGameLiveDataMore
import com.victoryzzi.android.apps.opgg.data.repository.SummonerRepo.requestSummonerLiveData
import com.victoryzzi.android.apps.opgg.extension.asCallbackFlow
import com.victoryzzi.android.apps.opgg.service.NetworkHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
class MainViewModel(application: Application) : AndroidViewModel(application) {
    val gameListAdapter = GameListAdapter()
    val leagueListAdapter = LeagueListAdapter()

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

    val responseSummonerLiveData: LiveData<Summoners> = requestSummonerLiveData.switchMap { summonerName ->
        liveData(Dispatchers.IO) {
            NetworkHelper.summonerService.loadSummoner(summonerName).asCallbackFlow().catch { e ->
                e.printStackTrace()
            }.collect {
                emit(it)
            }
        }
    }

    fun requestSummoner(summonerName: String) =
        requestSummonerLiveData.postValue(summonerName)

    fun requestGame(createDate: String) =
        requestGameLiveData.postValue(createDate)

    fun requestGameMore() =
        requestGameLiveDataMore.postValue(gameListAdapter.currentList.last().createDate)
}