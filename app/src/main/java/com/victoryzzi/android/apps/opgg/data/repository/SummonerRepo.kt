package com.victoryzzi.android.apps.opgg.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.victoryzzi.android.apps.opgg.extension.asCallbackFlow
import com.victoryzzi.android.apps.opgg.data.model.Summoners
import com.victoryzzi.android.apps.opgg.service.NetworkHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
object SummonerRepo {
    var preSummoners: Summoners? = null
    val requestSummonerLiveData = MutableLiveData<String>()
    val responseSummonerLiveData: LiveData<Summoners> = requestSummonerLiveData.switchMap { summonerName ->
        liveData(Dispatchers.IO) {
            NetworkHelper.summonerService.loadSummoner(summonerName).asCallbackFlow().catch { e ->
                e.printStackTrace()
            }.collect {
                preSummoners = it
                emit(it)
            }
        }
    }
}