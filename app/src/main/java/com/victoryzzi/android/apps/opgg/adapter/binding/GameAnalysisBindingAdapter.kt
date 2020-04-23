package com.victoryzzi.android.apps.opgg.adapter.binding

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.victoryzzi.android.apps.opgg.R
import com.victoryzzi.android.apps.opgg.getKDASpannable
import com.victoryzzi.android.apps.opgg.model.games.Champions
import com.victoryzzi.android.apps.opgg.ui.view.GamesAnalysis
import kotlinx.android.synthetic.main.layout_games_analysis.view.*
import java.util.*

/**
 * 최근 20게임 전적 분석
 */
@BindingAdapter("recentWinRate")
fun setRecentWinRate(view: GamesAnalysis, recentWinRate: IntArray?) {
    recentWinRate?.let {
        view.games_analysis_wins.text = String.format(
            view.resources.getString(R.string.games_analysis_recent_win_rate),
            it[0],
            it[1]
        )
    }
}

/**
 * 몇게임 분석인지 알려주는 타이틀 텍스트 세팅
 */
@BindingAdapter("analysisDescription")
fun setAnalysisDescriptionText(view: GamesAnalysis, gamesSize: Int?) {
    gamesSize?.let {
        view.games_analysis_description.text = String.format(
            view.resources.getString(R.string.games_analysis_description),
            it
        )
    }
}

/**
 * K / D / A 형식이지만
 * D 부분에 색상 추가를 위해 Spannable클래스 이용
 */
@BindingAdapter("kdaAnalysis")
fun setAnalysisKDA(view: GamesAnalysis, analysisKda: String?) {
    analysisKda?.let {
        view.games_analysis_kda.text = getKDASpannable(analysisKda)
    }
}

/**
 * 최근 20 게임 분석을 위해
 * Games 데이터 클래스에서 미리 결과를 받아옴
 */
@BindingAdapter("kdaRateAnalysis")
fun setAnalysisKDARate(view: GamesAnalysis, analysisKdaRate: String?) {
    analysisKdaRate?.let {
        view.games_analysis_kda_rate.text = analysisKdaRate
    }
}

/**
 * mostChampions을 한번에 세팅하기 위해 list 선언 후
 * forEachIndexed를 이용해 세팅
 */
@BindingAdapter("mostChampions")
fun setMostChampions(view: GamesAnalysis, mostChampions: List<Champions>?) {
    val imageViewList = listOf(
        view.games_analysis_most_picked_champion_image_first,
        view.games_analysis_most_picked_champion_image_second
    )
    val textViewList = listOf(
        view.games_analysis_most_picked_champion_rate_first,
        view.games_analysis_most_picked_champion_rate_second
    )
    mostChampions?.forEachIndexed { i, champion ->
        Glide.with(view)
            .load(champion.imageUrl)
            .into(imageViewList[i])
        textViewList[i].text = champion.getChampionsWinRate()
    }
}

/**
 * 해당 position의 스펠링을 모르고
 * String클래스는 대소문자를 구별하기 때문에
 * lowerCase로 바꿔서 이미지 넣어주기
 */
@BindingAdapter("positionImage")
fun setPositionImage(view: GamesAnalysis, positionName: String?) {
    positionName?.let {
        val imageId = when (positionName.toLowerCase(Locale.US)) {
            "bottom" -> R.drawable.icon_lol_bot
            "middle" -> R.drawable.icon_lol_mid
            "jungle" -> R.drawable.icon_lol_jng
            "support" -> R.drawable.icon_lol_sup
            "top" -> R.drawable.icon_lol_top
            else -> R.drawable.ic_default_item
        }

        view.games_analysis_positions_image.setImageResource(imageId)
    }
}

@BindingAdapter("positionPercent")
fun setPositionWinRate(view: GamesAnalysis, positionWinRate: String?) {
    positionWinRate?.let {
        view.games_analysis_positions_percent.text = it
    }
}