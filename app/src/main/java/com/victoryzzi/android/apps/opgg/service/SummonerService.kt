package com.victoryzzi.android.apps.opgg.service

import com.victoryzzi.android.apps.opgg.consts.SUMMONER_SERVICE_PATH
import com.victoryzzi.android.apps.opgg.consts.SUMMONER_SERVICE_URL
import com.victoryzzi.android.apps.opgg.data.model.Summoners
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Summoner API 정보 호출을 위한
 * Retrofit 서비스 인터페이스
 *
 * @param SUMMONER_SERVICE_URL = "/api/summoner/{summonerName}"
 * @param SUMMONER_SERVICE_PATH = "summonerName"
 */
interface SummonerService {
    @GET(SUMMONER_SERVICE_URL)
    fun loadSummoner(
        @Path(SUMMONER_SERVICE_PATH)
        summonerName: String
    ): Call<Summoners>
}