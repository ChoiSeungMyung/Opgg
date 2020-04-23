package com.victoryzzi.android.apps.opgg.model.games

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Champions(
    val imageUrl: String,
    val games: Int,
    val wins: Int
) {
    fun getChampionsWinRate() = "${((wins.toFloat() / games.toFloat()) * 100).toInt()}%"
}