package com.victoryzzi.android.apps.opgg.model.games

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Game(
    val gameId: String,
    val isWin: Boolean,
    val gameLength: String,
    val champion: Champion,
    val spells: List<Spell>,
    @Json(name = "peak") val perks: List<String>,
    val gameType: String,
    val stats: Stats,
    val createDate: String,
    val items: List<Item>
)

@JsonClass(generateAdapter = true)
data class Champion(
    val imageUrl: String
)

@JsonClass(generateAdapter = true)
data class Spell(
    val imageUrl: String
)

@JsonClass(generateAdapter = true)
data class General(
    val kill: Int,
    val death: Int,
    val assist: Int,
    val contributionForKillRate: String,
    val largestMultiKillString: String,
    val opScoreBadge: String
) {
    fun getKDA() = "$kill / $death / $assist"
}

@JsonClass(generateAdapter = true)
data class Stats(
    val general: General
)

@JsonClass(generateAdapter = true)
data class Item(
    val imageUrl: String
)