package com.victoryzzi.android.apps.opgg.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.victoryzzi.android.apps.opgg.R
import kotlinx.android.synthetic.main.layout_game_info.view.*

class GameInfo(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.layout_game_info, this)

//        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.GameInfo, 0, 0)
//
//        try {
////            game_info_wrapper.setBackgroundColor(
////                typedArray.getColor(
////                    R.styleable.GameInfo_bg,
////                    ContextCompat.getColor(context, R.color.colorSoftBlue)
////                )
////            )
////            game_info_wrapper.setBackgroundResource(typedArray.getResourceId(R.styleable.GameInfo_bg, R.color.colorSoftBlue))
////            game_is_win.text = typedArray.getText(R.styleable.GameInfo_isWin)
////            game_length.text = typedArray.getText(R.styleable.GameInfo_gameLength)
//        } finally {
//            typedArray.recycle()
//        }
    }
}