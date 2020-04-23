package com.victoryzzi.android.apps.opgg.adapter.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.victoryzzi.android.apps.opgg.R
import com.victoryzzi.android.apps.opgg.model.League

@BindingAdapter("leagueTier")
fun setLeagueTierImage(view: ImageView, imageUrl: String) {
    Glide.with(view)
        .load(imageUrl)
        .into(view)
}

/**
 * 리그 성적이 없다면 GONE 처리
 */
@BindingAdapter("winRate")
fun setLeagueWinRate(view: TextView, league: League) {
    val totalGames = league.wins + league.losses
    if (totalGames > 0) {
        val resources = view.resources
        val winRate: Int = ((league.wins.toFloat() / totalGames) * 100).toInt()
        view.text =
            String.format(
                resources.getString(R.string.summoner_win_rate),
                league.wins,
                league.losses,
                winRate
            )
    } else {
        view.visibility = View.GONE
    }
}