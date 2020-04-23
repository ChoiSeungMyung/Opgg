package com.victoryzzi.android.apps.opgg.data.model.games

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Champions(
    val imageUrl: String,
    val games: Int,
    val wins: Int
) {
//    챔피언별 승률 계산후 데이터바인딩을 위해 String 형태로 return 시켜줌
    fun getChampionsWinRate() = "${((wins.toFloat() / games.toFloat()) * 100).toInt()}%"
}