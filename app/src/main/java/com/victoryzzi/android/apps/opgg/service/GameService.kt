package com.victoryzzi.android.apps.opgg.service

import com.victoryzzi.android.apps.opgg.model.Game
import com.victoryzzi.android.apps.opgg.model.Games
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GameService {
    @GET("/api/summoner/genetory/matches")
    fun loadGames(
        @Query("lastMatch") createDate: String
    ): Call<Games>
}