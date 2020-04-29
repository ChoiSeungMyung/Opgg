package com.victoryzzi.android.apps.opgg.data.repository

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
object SummonerRepo {
    val requestSummonerLiveData = MutableLiveData<String>()
}