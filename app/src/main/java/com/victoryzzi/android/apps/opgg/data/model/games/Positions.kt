package com.victoryzzi.android.apps.opgg.data.model.games

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Positions(
    val games: Int,
    val wins: Int,
    val losses: Int,
    val positionName: String
) {
    fun getRecentWinRate(): IntArray = intArrayOf(wins, losses)
    fun getPositionWinRate() = "${((wins.toFloat() / games.toFloat()) * 100).toInt()}%"
}