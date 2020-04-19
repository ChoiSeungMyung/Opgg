package com.victoryzzi.android.apps.opgg.service

import com.victoryzzi.android.apps.opgg.model.Summoners
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SummonerService {
    @GET("/api/summoner/{summonerName}")
    fun loadSummoner(
        @Path("summonerName")
        summonerName: String
    ): Call<Summoners>
}