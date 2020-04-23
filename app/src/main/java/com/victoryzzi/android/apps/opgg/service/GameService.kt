package com.victoryzzi.android.apps.opgg.service

import com.victoryzzi.android.apps.opgg.consts.GAME_SERVICE_QUERY
import com.victoryzzi.android.apps.opgg.consts.GAME_SERVICE_URL
import com.victoryzzi.android.apps.opgg.data.model.games.Games
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Game API 정보 호출을 위한
 * Retrofit 서비스 인터페이스
 *
 * @param GAME_SERVICE_URL = "/api/summoner/genetory/matches"
 * @param GAME_SERVICE_QUERY = "lastMatch"
 */
interface GameService {
    @GET(GAME_SERVICE_URL)
    fun loadGames(
        @Query(GAME_SERVICE_QUERY)
        createDate: String
    ): Call<Games>
}