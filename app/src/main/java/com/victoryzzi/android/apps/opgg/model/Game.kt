package com.victoryzzi.android.apps.opgg.model

data class Game(
    val gameId: String,
    val isWin: Boolean,
    val gameLength: String,
    val champion: String,
    val opScoreBadge: String,
    val spells: List<String>,
    val kills: String,
    val deaths: String,
    val assists: String,
    val contributionForKillRate: String,
    val gameType: String,
    val createDate: String,
    val items: List<String>,
    val largestMultiKill: String
){
    fun getKDA() = "$kills / $deaths / $assists"
}