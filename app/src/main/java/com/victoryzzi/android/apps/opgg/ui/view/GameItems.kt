package com.victoryzzi.android.apps.opgg.ui.view

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.victoryzzi.android.apps.opgg.R
import kotlinx.android.synthetic.main.layout_game_items.view.*

class GameItems(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.layout_game_items, this)
        game_items_root.children.forEach {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                it.clipToOutline = true
            }
        }
    }
}