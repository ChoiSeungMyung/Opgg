package com.victoryzzi.android.apps.opgg.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.victoryzzi.android.apps.opgg.R
import kotlinx.android.synthetic.main.layout_profile.view.*

class Profile(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.layout_profile, this)

        profile_image.clipToOutline = true
        profile_text.clipToOutline = true
    }
}