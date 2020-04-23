package com.victoryzzi.android.apps.opgg.adapter.binding

import android.graphics.Typeface
import android.util.TypedValue
import android.view.View
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.victoryzzi.android.apps.opgg.R
import com.victoryzzi.android.apps.opgg.ui.view.Profile
import kotlinx.android.synthetic.main.layout_profile.view.*

/**
 * Profile, layout_profile에 해당하는
 * BindingAdapter
 */


@BindingAdapter("profileImage")
fun setProfileImage(view: Profile, championImageUrl: String?) {
    Glide.with(view)
        .load(championImageUrl)
        .circleCrop()
        .into(view.profile_image)
}

/**
 * 뷰 특성에 따라 뱃지 텍스트가 다르기 때문에
 * 뱃지 텍스트 마다 다른 속성값과 다른 배경 적용
 */
@BindingAdapter("profileText")
fun setBadge(view: Profile, msg: String?) {
    if (msg != null) {
        when (msg) {
            "ACE" -> {
                view.profile_text.apply {
                    visibility = View.VISIBLE
                    setBackgroundResource(R.drawable.bg_badge_ace)
                    setTypeface(typeface, Typeface.BOLD)
                    setTextSize(
                        TypedValue.COMPLEX_UNIT_PX,
                        view.resources.getDimension(R.dimen.sp_10)
                    )
                    text = msg
                }
            }

            "MVP" -> {
                view.profile_text.apply {
                    visibility = View.VISIBLE
                    setTextSize(
                        TypedValue.COMPLEX_UNIT_PX,
                        view.resources.getDimension(R.dimen.sp_10)
                    )
                    setBackgroundResource(R.drawable.bg_badge_mvp)
                    setTypeface(typeface, Typeface.BOLD)
                    text = msg
                }
            }
            "" -> {
                view.profile_text.visibility = View.GONE
            }
            else -> {
                view.profile_text.apply {
                    visibility = View.VISIBLE
                    setTextSize(
                        TypedValue.COMPLEX_UNIT_PX,
                        view.resources.getDimension(R.dimen.sp_12)
                    )
                    setBackgroundResource(R.drawable.bg_badge_level)
                    text = msg
                }
            }
        }
    }
}