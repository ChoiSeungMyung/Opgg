package com.victoryzzi.android.apps.opgg.data.model.games

import com.squareup.moshi.JsonClass
import com.victoryzzi.android.apps.opgg.extension.rounded

@JsonClass(generateAdapter = true)
data class Games(
    var games: List<Game>,
    val champions: List<Champions>,
    val positions: List<Positions>
) {
    private fun getKillAverage() =
        games.map { it.stats.general.kill }.average().toBigDecimal().rounded(2)

    private fun getDeathAverage() =
        games.map { it.stats.general.death }.average().toBigDecimal().rounded(2)

    private fun getAssistsAverage() =
        games.map { it.stats.general.assist }.average().toBigDecimal().rounded(2)

    fun getKDAAverage() = "${getKillAverage()} / ${getDeathAverage()} / ${getAssistsAverage()}"

    fun getKDARate() =
        "${((getKillAverage() + getAssistsAverage()) / getDeathAverage()).rounded(2)}:1"

    fun getMostChampions() = champions.sortedByDescending { it.wins / it.games }.take(2)

    //List.addAll()을 구현
    fun addAllGames(target: Games) {
        val copyList = games.toMutableList()
        copyList.addAll(target.games)
        games = copyList
    }
}