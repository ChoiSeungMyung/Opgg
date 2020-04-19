package com.victoryzzi.android.apps.opgg.model

import android.content.res.Resources
import com.squareup.moshi.JsonClass
import com.victoryzzi.android.apps.opgg.R

@JsonClass(generateAdapter = true)
data class Summoners(
    val summoner: Summoner
)

@JsonClass(generateAdapter = true)
data class Summoner(
    val name: String,
    val level: String,
    val profileImageUrl: String,
    val leagues: List<League>
)

@JsonClass(generateAdapter = true)
data class League(
    val wins: Int,
    val losses: Int,
    val tierRank: TierRank
)

@JsonClass(generateAdapter = true)
data class TierRank(
    val name: String,
    val tier: String,
    val imageUrl: String,
    val lp: Int
) {
    fun getLp(): String {
        return if(lp > 0) {
            String.format("%,d LP", lp)
        } else {
            "- LP"
        }
    }
}