package com.victoryzzi.android.apps.opgg.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.bumptech.glide.Glide
import com.victoryzzi.android.apps.opgg.R
import kotlinx.android.synthetic.main.layout_game_items.view.*

class GameItems(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.layout_game_items, this)
        game_items_root.children.forEach {
            it.clipToOutline = true
        }
    }
}