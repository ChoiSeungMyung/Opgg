package com.victoryzzi.android.apps.opgg.ui.view

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.victoryzzi.android.apps.opgg.R
import kotlinx.android.synthetic.main.layout_games_analysis.view.*

class GamesAnalysis(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.layout_games_analysis, this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            games_analysis_most_picked_champion_image_first.clipToOutline = true
            games_analysis_most_picked_champion_image_second.clipToOutline = true
        }
    }
}