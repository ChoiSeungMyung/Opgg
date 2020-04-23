package com.victoryzzi.android.apps.opgg.ui.view

import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.victoryzzi.android.apps.opgg.R
import kotlinx.android.synthetic.main.layout_game_spells.view.*

class GameSpells(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.layout_game_spells, this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            spells_first.clipToOutline = true
            spells_second.clipToOutline = true
            perk_first.clipToOutline = true
            perk_second.clipToOutline = true
        }
    }
}