package com.victoryzzi.android.apps.opgg.service

import com.squareup.moshi.Moshi
import com.victoryzzi.android.apps.opgg.BASE_URL
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@ExperimentalCoroutinesApi
object NetworkHelper {
    private val okhttp by lazy {
        OkHttpClient.Builder().build()
    }

    private val moshi by lazy {
        Moshi.Builder()
            .build()
    }

//    BASE_URL = "https://codingtest.op.gg"
    private val retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .client(okhttp)
            .build()
    }

    val gameService: GameService by lazy {
        retrofit.create(
            GameService::class.java)
    }

    val summonerService: SummonerService by lazy {
        retrofit.create(
            SummonerService::class.java
        )
    }
}