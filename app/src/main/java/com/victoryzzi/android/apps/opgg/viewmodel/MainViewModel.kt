package com.victoryzzi.android.apps.opgg.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.victoryzzi.android.apps.opgg.adapter.list.GameListAdapter
import com.victoryzzi.android.apps.opgg.adapter.list.LeagueListAdapter
import com.victoryzzi.android.apps.opgg.data.repository.GameRepo
import com.victoryzzi.android.apps.opgg.data.repository.SummonerRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainViewModel(application: Application) : AndroidViewModel(application) {
    val gameListAdapter = GameListAdapter()
    val leagueListAdapter = LeagueListAdapter()

    fun requestSummoner(summonerName: String) =
        SummonerRepo.requestSummonerLiveData.postValue(summonerName)

    fun requestGame(createDate: String) =
        GameRepo.requestGameLiveData.postValue(createDate)

    fun requestGameMore() =
        GameRepo.requestGameLiveDataMore.postValue(gameListAdapter.currentList.last().createDate)
}