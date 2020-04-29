package com.victoryzzi.android.apps.opgg.data.repository

import androidx.lifecycle.MutableLiveData
import com.victoryzzi.android.apps.opgg.data.model.games.Games
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
object GameRepo {
    var preGames: Games? = null
    val requestGameLiveData = MutableLiveData<String>()
    val requestGameLiveDataMore = MutableLiveData<String>()
}