package com.victoryzzi.android.apps.opgg.service

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.victoryzzi.android.apps.opgg.model.Game
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@ExperimentalCoroutinesApi
object GameRepo {
    val okhttp by lazy {
        OkHttpClient.Builder().build()
    }

    val moshi by lazy {
        Moshi.Builder()
            .build()
    }

    val retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://codingtest.op.gg")
            .client(okhttp)
            .build()
    }

    val gameService: GameService by lazy {
        retrofit.create(GameService::class.java)
    }
}